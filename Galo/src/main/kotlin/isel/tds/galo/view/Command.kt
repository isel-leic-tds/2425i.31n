package isel.tds.galo.view

import isel.tds.galo.model.*
import isel.tds.galo.storage.Storage

class Command(
    val isToFinish: Boolean=false,
    val execute: (args: List<String>, game: Game) -> Game
        = { _, game -> game}
)

private val play = Command() {args, game ->
        requireNotNull(game) { "Game not started" }
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toIntOrNull()?.toPositionOrNull()) { "Invalid position $arg" }
        game.play(pos)
    }

private fun storageCommand(exec: (Game, String) -> Game) =
    Command() { args, game ->
        val name = requireNotNull(args.firstOrNull()) { "Missing name" }
        require(name.isNotBlank() && name.all { it.isLetter() }) {
            "Invalid name $name"
        }
        exec(game, name)
    }

typealias GameStorage = Storage<String, Game>

fun getCommands(st: GameStorage): Map<String, Command> =
    mapOf(
        "EXIT" to Command(isToFinish = true),
        "NEW" to Command {_, game -> game.newBoard()},
        "PLAY" to play,
        "SHOW" to Command{ _, game -> game.also { it.showScore() }},
        "SAVE" to storageCommand { game, name -> game.also {
            try { st.create(name, game) }
            catch (e: Exception) { error("Game $name already exists") }
        } },
        "LOAD" to storageCommand { _, name ->
            checkNotNull(st.read(name)) { "Game $name not found" }
        }
    )