package Views

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text

@Composable
fun GameHome(){
    Div { Text("Enter lobby code here:") }
    Div {
        Input(InputType.Text)
    }
    Div {
        Button {
            Text("Join Lobby")
        }
    }
    Div {
        Button(attrs = { classes() }) {
            Text("Create New Lobby")
        }
    }
}