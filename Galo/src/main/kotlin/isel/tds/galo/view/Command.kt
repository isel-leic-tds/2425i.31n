package isel.tds.galo.view

import isel.tds.galo.model.*

class Command(
    val argsSyntax: String = "",
    val isToFinish: Boolean=false,
    val execute: (args: List<String>, clash: Clash) -> Clash
        = { _, clash -> clash}
)

private val play = Command() {args, clash ->
        requireNotNull(clash is ClashRun) { "Game not started" }
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toIntOrNull()?.toPositionOrNull()) { "Invalid position $arg" }
        clash.play(pos)
    }

private fun beginCommand(exec: Clash.(Name) -> Clash) =
    Command("<name>")
//    { args ->
    { args, clash ->
        val word = requireNotNull(args.firstOrNull()) {"Missing name"}
//        exec(Name(word))
        clash.exec(Name(word))
    }

fun getCommands(): Map<String, Command> =
    mapOf(
        "EXIT" to Command(isToFinish = true),
        "NEW" to Command {_, clash -> clash.newBoard()},
        "PLAY" to play,
        "SHOW" to Command{ _, clash -> clash.also {
            (it as? ClashRun)?.game?.showScore()
        }},
        "START" to beginCommand { name -> this.startClash(name) },
        "JOIN" to beginCommand { name -> this.joinClash(name) },
        "REFRESH" to Command { _, clash -> clash.refresh() },

    )