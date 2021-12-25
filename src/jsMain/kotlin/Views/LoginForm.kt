package Views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun LoginForm(){
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    Div {
        Div {
            Input(InputType.Text, attrs = {
                onInput {
                    username = it.value
                }
            })
        }
        Div{
            Input(InputType.Password, attrs = {
                onInput {
                    password = it.value
                }
            })
        }
        Div {
            LoginButton {

            }
        }

    }
}

@Composable
fun LoginButton(action: () -> Unit){
    Button(attrs = { onClick{ action } }) {
        Text("Login")
    }
}
@Composable
fun UsernameField(){
    Input(InputType.Text)
}
@Composable
fun PasswordField(){
    Input(InputType.Password)
}