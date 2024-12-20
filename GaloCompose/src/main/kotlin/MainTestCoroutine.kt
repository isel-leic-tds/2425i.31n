import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*


fun testLog(lab: String) = println("$lab: ${Thread.currentThread().name}")

fun main2(){
    testLog("inside main")
    runBlocking {
        testLog("inside runBlocking")
        launch(Dispatchers.Unconfined) {
            testLog("inside Corotine")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val scope = rememberCoroutineScope()
        var job by remember { mutableStateOf<Job?>(null) }
        val clickable = job == null
        Row {
            Button(enabled = clickable, onClick = {
                println("Clicked")
                job = scope.launch(Dispatchers.Default) {
                    testLog("inside Corotine")
                    repeat(5) { print('.'); delay(1000) }
                    job = null
                }
            } ) { Text("Click me") }
            Button(enabled = !clickable, onClick = {
                job?.cancel().also { job = null }
            } ) { Text("Enable Click") }
        }
    }
}