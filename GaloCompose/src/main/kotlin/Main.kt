import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App(window: ComposeWindow) {
    var text by remember { mutableStateOf("Hello, World!") }
    var text2 = mutableStateOf("Hello, World2!")
    println("App lambda")
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                println("Row Lambda")
                Button(
                    onClick = {
                        text = "Hello, Desktop! ${System.currentTimeMillis()}"
                        window.title = text
                    },
                    modifier = Modifier.fillMaxWidth().background(color = Color.Red)
                ) {
                    log("Button Text")
                    Text(text)
                }
            }
            Row {
                Button(onClick = {
                    text2.value = "Hello, Desktop! ${System.currentTimeMillis()}"
                }) {
                    Text(text2.value)
                }
            }
        }
    }
}

fun main() {
    log("main")
    application {
        log("application")
        Window(onCloseRequest = ::exitApplication, title = "Xpto") {
            //this.window.title= "xpto2"
            App(this.window)
        }
    }
}


fun log(label: String) {
    println("$label thread=${Thread.currentThread().name}")
}

