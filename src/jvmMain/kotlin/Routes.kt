import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.routing.*
import kotlinx.html.HTML

fun Application.addAppRoute(){
    routing {
        authenticate{
            get("/app/game/home") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
                log.info("A user successfully accessed /app/game/home")
            }
        }
        static("/static") {
            resources()
        }
    }
}
