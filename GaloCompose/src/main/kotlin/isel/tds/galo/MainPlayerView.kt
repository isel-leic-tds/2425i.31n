package isel.tds.galo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import isel.tds.galo.model.Player
import isel.tds.galo.ui.PlayerView

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Xpto") {
        PlayerApp()
    }
}

@Composable
fun PlayerApp() {
    var player by remember { mutableStateOf(Player.X) }
    MaterialTheme {
        Column {
            PlayerView(player, 25.dp)
            Button(onClick = {
                player = player.other
            }) {
                Text("Change Player")
            }
        }
    }

}
