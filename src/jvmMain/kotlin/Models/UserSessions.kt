package Models

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

data class UserSession(
    val name: String,
    val roles: Set<String> = emptySet()
) : Principal

fun Application.addAuth(){
    install(Sessions) {
        cookie<UserSession>("ktor_session_cookie", SessionStorageMemory())
    }
    install(Authentication) {
        session<UserSession> {
            validate { session: UserSession ->
                session
            }
        }
    }
/*
    routing {

            post("/api/login") {
                val userName = call.principal<UserIdPrincipal>()?.name.toString()
                call.sessions.set(UserSession(name = userName))
                call.respondRedirect("/app/private")
            }

    }
    */
}