package Views

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.*

@Composable
fun HelloWorld(){
     Div({ classes("testDiv") }){
        Div({ classes("helloWorld") }) {
            Text("Hello, World!")
        }

    }

}

@Composable
fun GameHome(){
    Div({classes("screenDiv")}) {
        Div({classes("formDiv")}) {
            Div({classes("contentDiv")}){
                Div ({classes("infoText")}){ Text("Enter lobby code here:") }
                Input(InputType.Text){classes("textBox")}
                Button({ classes("buttonStyleBlue") }) {
                    Text("Join Lobby")
                }
                Button({ classes("buttonStyleGreen") }) {
                    Text("Create New Lobby")
                }
            }


        }
    }



}
