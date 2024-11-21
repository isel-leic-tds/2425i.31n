package isel.tds.galo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import isel.tds.galo.model.Board
import isel.tds.galo.model.Player
import isel.tds.galo.model.play
import isel.tds.galo.ui.GridView
import isel.tds.galo.ui.PlayerView


fun main() = application {
    Window(onCloseRequest = ::exitApplication,
        title = "Galo",
        state = WindowState(size = DpSize.Unspecified)) {
        GridApp()
    }
}

@Composable
fun GridApp() {
    var board by remember { mutableStateOf(Board()) }
    MaterialTheme {
        GridView(board?.moves, onClickCell = { pos ->
            try {
                board = board.play(pos)
            } catch (ex: Exception) {
                println(ex.message)
            }
        })
    }

}
