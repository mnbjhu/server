package Views

import androidx.compose.runtime.*
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text

@Composable
fun Echo(){
    var receivedText by remember { mutableStateOf("") }
    var inputText by remember { mutableStateOf("") }
    var myMessage: String? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()
    remember {
        val client = HttpClient(Js) {
            install(WebSockets)
        }
        coroutineScope.launch(Dispatchers.Unconfined) {
            client.webSocket(
                method = HttpMethod.Get,
                host = "127.0.0.1",
                port = 8080,
                path = "/api/echo") {
                while(true) {
                    if(!incoming.isEmpty) {
                        val raw = incoming.receive()
                        if (raw is Frame.Close) console.log(raw.readReason())
                        val othersMessage = raw as? Frame.Text
                        if (othersMessage != null) receivedText += "${othersMessage.readText()}\n"
                    }
                    if(myMessage != null) {
                        console.log("Attempting to send a message!")
                        send(myMessage.toString())
                        myMessage = null
                    }
                    delay(100)
                }
            }
        }
    }


    Text(receivedText)
    Input(InputType.Text){
        onInput { inputText = it.value }
    }
    Button(attrs = { onClick { myMessage = inputText; inputText = "" }}){
        Text("Send!")
    }
    Div{
        Text("Input text: $inputText")
    }
    Div{
        Text("My message: $myMessage")
    }

}

@Composable
fun Count(){
    var number by remember { mutableStateOf(0) }
    remember {
        GlobalScope.launch {
            while (true){
                delay(1000)
                number++
            }
        }
    }
    Text("$number")
}