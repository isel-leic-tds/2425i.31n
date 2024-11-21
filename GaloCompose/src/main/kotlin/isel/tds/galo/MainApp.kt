package isel.tds.galo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import isel.tds.galo.model.Game
import isel.tds.galo.model.newBoard
import isel.tds.galo.model.play
import isel.tds.galo.ui.GridView
import isel.tds.galo.ui.ScoreDialog
import isel.tds.galo.ui.StatusBar

fun ApplicationScope.myExitApplication(){
    //Ask for confirmation
    //TODO:
    this.exitApplication()
}
fun main() = application {
    Window(onCloseRequest = ::myExitApplication,
        title = "Galo",
        state = WindowState(size = DpSize.Unspecified)) {

        GridApp(::myExitApplication)
    }
}

@Composable
fun FrameWindowScope.GridApp(onExit: ()->Unit) {
    var game by remember { mutableStateOf(Game()) }
    var viewScore by remember { mutableStateOf(false)}

    MaterialTheme {
        MenuBar {
            Menu("Game"){
                Item("New Board" , onClick = { game = game.newBoard() })
                Item("Show Score" , onClick = { viewScore=!viewScore })
                Item("Exit", onClick = onExit)
            }
        }
        Column {
            GridView(game?.board?.moves, onClickCell = { pos ->
                try {
                    game = game.play(pos)
                } catch (ex: Exception) {
                    println(ex.message)
                }
            })
            StatusBar(game.board)
        }
        if(viewScore)
            ScoreDialog(game.score, onClose={ viewScore=false})
    }

}
