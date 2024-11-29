package isel.tds.galo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import isel.tds.galo.model.*
import isel.tds.galo.storage.GameSerializer
import isel.tds.galo.storage.MongoDriver
import isel.tds.galo.storage.MongoStorage
import isel.tds.galo.view.InputName

class AppViewModel(driver: MongoDriver) {

    var clash by mutableStateOf(Clash(MongoStorage<Name, Game>("games", driver, GameSerializer)))
        private set
    var showViewScore by mutableStateOf(false)
        private set
    val score: Score get() = (clash as ClashRun).game.score
    var inputName by mutableStateOf<InputName?>(null) //StartOrJoinDialog
        private set
    val hasClash: Boolean get() = clash is ClashRun
    var errorMessage by mutableStateOf<String?>(null) //ErrorDialog state
        private set
    val name: Name get() = (clash as ClashRun).id

    val board: Board? get() = (clash as? ClashRun)?.game?.board
    val sidePlayer: Player? get() = (clash as? ClashRun)?.sidePlayer

    private fun exec(fx: Clash.() -> Clash): Unit =
        try {
            //throw Exception("My blow up")
            clash = clash.fx()
        } catch (e: Exception) {        // Report exceptions in ErrorDialog
            e.printStackTrace()
            errorMessage = e.message
        }

    fun hideError() {
        errorMessage = null
    }

    fun newBoard() = exec(Clash::newBoard)
    fun play(pos: Position) = exec { play(pos) }
    fun refresh() = exec(Clash::refresh)

    fun toggleViewScore() {
        showViewScore = !showViewScore
    }

    fun hideViewScore() {
        showViewScore = false
    }

    fun openStartDialog() { inputName = InputName.ForStart }
    fun openJoinDialog() { inputName = InputName.ForJoin }
    fun closeStartOrJoinDialog() { inputName = null }

    fun start(name: Name){ cleanupAndExec { startClash(name)}}
    fun join(name: Name) { cleanupAndExec { joinClash(name) }}

    private fun cleanupAndExec(action: Clash.()->Clash) {
        closeStartOrJoinDialog()
        exec { action() }
    }
}