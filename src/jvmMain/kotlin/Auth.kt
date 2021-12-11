import Models.User
import Models.Users
import io.ktor.application.*
import io.ktor.auth.*
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.addAuth(){
    install(Authentication) {
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                val count = transaction {
                    Users.select{ (Users.username eq credentials.name) and
                            (Users.password eq credentials.password) }
                        .count()
                }

                if (count > 0) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}
