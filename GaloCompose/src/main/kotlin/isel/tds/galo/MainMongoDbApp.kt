package isel.tds.galo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import isel.tds.galo.storage.MongoDriver
import isel.tds.galo.view.*
import isel.tds.galo.viewmodel.AppViewModel

fun ApplicationScope.myExitApplication() {
    //Ask for confirmation
    //TODO:
    this.exitApplication()
}

fun main() =
    MongoDriver("galo").use { driver ->
        application {
            Window(
                onCloseRequest = ::myExitApplication,
                title = "Galo",
                state = WindowState(size = DpSize.Unspecified)
            ) {
                GridApp(driver, ::myExitApplication)
            }
        }
    }

@Composable
fun FrameWindowScope.GridApp(driver: MongoDriver, onExit: () -> Unit) {
    val scope = rememberCoroutineScope()
    val vm = remember { AppViewModel(driver, scope) }

    MaterialTheme {
        MenuBar {
            Menu("Game") {
                Item("Start game", onClick = vm::openStartDialog)
                Item("Join game", onClick = vm::openJoinDialog)
                Item("Refresh", enabled = vm.hasClash, onClick = vm::refresh)
                Item("New Board", onClick = vm::newBoard)
                Item("Show Score", onClick = vm::toggleViewScore)
                Item("Exit", onClick = onExit)
            }
        }
        Column {
            GridView(vm.board?.moves, onClickCell = vm::play)
            StatusBar(vm.board, vm.sidePlayer)
        }
        if (vm.showViewScore)
            ScoreDialog(vm.score, vm.name, onClose = vm::hideViewScore)
        vm.inputName?.let {
            StartOrJoinDialog(
                type = it,
                onCancel = vm::closeStartOrJoinDialog,
                onAction = if (it == InputName.ForStart) vm::start else vm::join
            )
        }
        vm.errorMessage?.let { ErrorDialog(it, onClose = vm::hideError) }
        if (vm.isWaiting) WaitingIndicator()
    }

}
