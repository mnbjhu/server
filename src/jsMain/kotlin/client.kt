
import Views.LoginForm
import app.softwork.routingcompose.BrowserRouter
import app.softwork.routingcompose.HashRouter
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
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
fun main() {
    kotlinext.js.require("./app.css")
    renderComposable(rootElementId = "root"){
        BrowserRouter(initRoute = "/") { // or BrowserRouter(initRoute = "/users") {
            route("app") {
                addRoutes()
            }

        }
    }
}
