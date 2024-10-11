package isel.tds.galo.view

import isel.tds.galo.model.Board
import isel.tds.galo.model.play

abstract class Command(val isToFinish: Boolean=false) {
    abstract fun execute(args: List<String>, board: Board?): Board
//Alternativa a ter a pro definida no construtor
//    abstract val isToFinish: Boolean
}

object Exit : Command(isToFinish = true) {
    override fun execute(args: List<String>, board: Board?): Board {
        return Board()
    }
//    override val isToFinish = true
}

object New : Command() {
    override fun execute(args: List<String>, board: Board?): Board {
        return Board()
    }
}

object Play : Command() {
    override fun execute(args: List<String>, board: Board?): Board {
        requireNotNull(board) { "Game not started" }
        val arg = requireNotNull(args.firstOrNull()) { "Missing position" }
        val pos = requireNotNull(arg.toIntOrNull()) { "Invalid pos $arg" }

        return board.play(pos)
    }
}

fun getCommands(): Map<String, Command> =
    mapOf(
        "EXIT" to Exit,
        "NEW" to New,
        "PLAY" to Play
    )