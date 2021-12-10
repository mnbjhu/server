import Models.Users
import Models.getUserById
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.HTML
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


fun setupDatabase(){
    Database.connect(
        url = "jdbc:mysql://31.14.41.229:3306/dev",
        driver = "com.mysql.jdbc.Driver",
        user = "mnbjhu",
        password = "password"
    )
    transaction {
        SchemaUtils.create(Users)
    }


}
fun Application.addAppRoute(){
    routing {
        get("/") {
            call.respondHtml(HttpStatusCode.OK, HTML::index)
        }
        static("/static") {
            resources()
        }
    }
}
fun Application.addUserRoute(){
    routing {
        get("/api/listusers"){
            try {
                val x = transaction {
                    Users.slice(Users.id.count()).selectAll().single()[Users.id.count()]
                }
                call.respond(HttpStatusCode.OK, "$x")
            }
            catch (e: Exception){ call.respond(HttpStatusCode.OK, "${e.stackTrace.joinToString("\n")}") }

        }
        get("/api/test"){
            call.respond(HttpStatusCode.OK, "Test")
        }

        get("/api/users/{user}") {
            val param = call.parameters["user"]
            try {
                val id = param!!.toInt()
                call.respond(HttpStatusCode.OK, getUserById(id))
            }
            catch (e: Exception){
                when(e){

                    is NullPointerException -> call.respond(
                        HttpStatusCode.BadRequest,
                        "No 'id' found in request."
                    )
                    is NumberFormatException -> call.respond(
                        HttpStatusCode.NotAcceptable,
                        "api/users/{user} accepts an integer as an argument but '$param' was found."
                    )
                    is NotFoundException -> call.respond(
                        HttpStatusCode.NotFound,
                        "No user found with id: $param"
                    )
                }
            }
        }

    }
}