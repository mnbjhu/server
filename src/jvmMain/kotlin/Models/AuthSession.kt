package Models

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.sessions.*
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object AuthSession: Table() {
    val id = AuthSession.integer("id").autoIncrement()
    val password = AuthSession.varchar("password", 60)
    override val primaryKey = PrimaryKey(id)
}
fun createNewSession() = transaction {
    val password = getRandomPassword()
    val id = AuthSession.insert{
        it[AuthSession.password] = password
    } get AuthSession.id
    SessionKey(id, password)
}

data class SessionKey(val id: Int, val password: String): Principal{
    fun isValid() = transaction {
        password == AuthSession.select { AuthSession.id eq this@SessionKey.id }.first()[AuthSession.password]
    }
}

fun getRandomPassword() = (1..60)
    .map { "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".random() }
    .joinToString("")

fun Application.addSessionAuth(){
    install(Sessions) {
        cookie<SessionKey>("ktor_session_cookie", SessionStorageMemory()){
            cookie.path = "/"
        }
    }
    install(Authentication) {
        session<SessionKey>{
            challenge{
                val key = createNewSession()
                call.sessions.set(key)
            }
            validate { session->

                log.info("Authenticating session: id = ${session.id}, password = ${session.password}")
                if (session.isValid()) session
                else {
                    val key = createNewSession()
                    sessions.set(key)
                    key
                }
            }

        }

    }

}