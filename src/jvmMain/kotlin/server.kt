
import Models.addNewAuth
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import io.ktor.serialization.*
import kotlinx.html.*
import kotlinx.serialization.json.Json

fun test(){

}
fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    
    body {
        div { id = "root2" }/*
        div {

            id = "root"
        }*/
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
        addNewAuth()
        addAppRoute()
        addUserRoute()
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
    .start(wait = true)
}