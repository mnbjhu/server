
import Views.LoginForm
import app.softwork.routingcompose.HashRouter
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {

    kotlinext.js.require("./app.css")
    renderComposable(rootElementId = "root") {

    }

}
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
HashRouter(initRoute = "/users") { // or BrowserRouter(initRoute = "/users") {
            route("/users") {
                int { userID ->
                    Text("User with $userID")
                }
                noMatch {
                    Text("User list")
                }
            }
            noMatch {
                Text("Hello World")
            }
        }
 */