

import Models.addAuth
import Models.addSessionAuth
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import io.ktor.serialization.*
import io.ktor.websocket.*
import kotlinx.html.*
import kotlinx.serialization.json.Json


fun HTML.index() {
    head {
        title("That's the pizza!")
    }
    
    body {
        div { id = "root" }
        script(src = "/static/js.js") {}
    }

}

fun main() {
    embeddedServer(Jetty,
        port = 8080,
        host = "0.0.0.0",
        watchPaths = listOf("classes", "resources")
    ) {
        setupDatabase()
        install(ContentNegotiation){
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
        install(WebSockets)
        install(StatusPages){
            exception<Throwable> {
                call.respondText(
                    it.localizedMessage,
                    ContentType.Text.Plain,
                    HttpStatusCode.InternalServerError
                )
            }
        }
        addSessionAuth()
        addAppRoute()
        addUserRoute()
    }
    .start(wait = true)
}