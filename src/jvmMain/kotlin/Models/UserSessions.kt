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

fun Application.addNewAuth(){
    install(Sessions) {
        cookie<UserSession>("ktor_session_cookie", SessionStorageMemory())
    }
    install(Authentication) {
        session<UserSession> {
            validate { session: UserSession ->
                session
            }
            challenge {
                call.respondRedirect("/login")
            }
        }
    }
    routing {
        get("/login") { /* Show login page */ }

        post("/login") {
            val credentials = call.receive<Credentials>()
            val count = transaction {
                Users.select{ (Users.username eq credentials.username) and
                        (Users.password eq credentials.password) }
                    .count()
            }
            if (count > 0) {
                // Store principal in session
                call.sessions.set(UserSession(credentials.username))
                call.respondRedirect("/")
            } else {
                // Stay on login page
                call.respondRedirect("/login")
            }
        }
    }
}