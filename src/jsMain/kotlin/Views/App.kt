package Views


import androidx.compose.runtime.*
import io.ktor.client.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.*

@Composable
fun CenteredScreenDiv(content: @Composable()() -> Unit){
    Div({classes("screenDiv")}) {
        Div({classes("formDiv")}) {
            Div({classes("contentDiv")}){
                content()
            }
        }
    }
}


@Composable
fun App() {
    var current: String? by remember { mutableStateOf(null) }
    val clientContext = rememberCoroutineScope()
    clientContext.launch {
        val client = HttpClient()

    }
    CenteredScreenDiv {
        if(current == null){

        }
    }
}
