import Views.LoginForm
import androidx.compose.runtime.Composable
import app.softwork.routingcompose.NavBuilder
import org.jetbrains.compose.web.dom.Text

@Composable
fun NavBuilder.addRoutes(){
    addLoginRoute()
    addAuthenticatedPage()
}

@Composable
fun NavBuilder.addLoginRoute(){
    route("login"){
        noMatch {
            LoginForm()
        }
    }
}

@Composable
fun NavBuilder.addAuthenticatedPage(){
    route("private"){
        noMatch {
            Text("Hello, World!")
        }
    }
}