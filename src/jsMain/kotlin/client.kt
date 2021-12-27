
import Views.LoginForm
import app.softwork.routingcompose.BrowserRouter
import app.softwork.routingcompose.HashRouter
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.GlobalScope
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.WebSocket

/*
fun main() {
    kotlinext.js.require("./app.css")
    renderComposable(rootElementId = "root") {
        Div{
            LoginForm()
        }
    }

}
*/
/*
val client = HttpClient(Js) {
    install(WebSockets) {

    }
}*/
suspend fun main() {
    kotlinext.js.require("./app.css")
/*
    client.webSocket(method = HttpMethod.Get, host = "127.0.0.1", port = 8080, path = "/api/echo") {
        while(true) {
            console.log("closing")
            val raw = incoming.receive()
            if(raw is Frame.Close) console.log(raw.readReason())
            val othersMessage = raw as? Frame.Text
            if(othersMessage != null) console.log(othersMessage.readText())
            if(true) {
                console.log("Attempting to send a message!")
                send("myMessage")
            }

        }
    }*/

    renderComposable(rootElementId = "root"){
        BrowserRouter(initRoute = "/") { // or BrowserRouter(initRoute = "/users") {
            route("app") {
                addRoutes()
            }
        }
    }
}

