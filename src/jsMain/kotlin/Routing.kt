
import Views.App
import androidx.compose.runtime.Composable
import app.softwork.routingcompose.NavBuilder

@Composable
fun NavBuilder.addRoutes(){
    addGameRoute()
}

@Composable
fun NavBuilder.addGameRoute(){
    route("game"){
        route("home"){
            App()
        }
    }
}
