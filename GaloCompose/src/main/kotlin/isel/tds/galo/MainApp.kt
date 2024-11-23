package isel.tds.galo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import isel.tds.galo.view.GridView
import isel.tds.galo.view.ScoreDialog
import isel.tds.galo.view.StatusBar
import isel.tds.galo.viewmodel.AppViewModel

//fun ApplicationScope.myExitApplication(){
//    //Ask for confirmation
//    //TODO:
//    this.exitApplication()
//}
//fun main() = application {
//    Window(onCloseRequest = ::myExitApplication,
//        title = "Galo",
//        state = WindowState(size = DpSize.Unspecified)) {
//
//        GridApp(::myExitApplication)
//    }
//}
//
//@Composable
//fun FrameWindowScope.GridApp(onExit: ()->Unit) {
//
//    val vm = remember { AppViewModel() }
//
//    MaterialTheme {
//        MenuBar {
//            Menu("Game"){
//                Item("New Board" , onClick = vm::newBoard)
//                Item("Show Score" , onClick = vm::toggleViewScore)
//                Item("Exit", onClick = onExit)
//            }
//        }
//        Column {
//            GridView(vm.game?.board?.moves, onClickCell = vm::play)
//            StatusBar(vm.game.board)
//        }
//        if(vm.showViewScore)
//            ScoreDialog(vm.game.score, onClose=vm::hideViewScore)
//    }
//
//}
