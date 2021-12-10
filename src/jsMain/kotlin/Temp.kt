import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.jetbrains.compose.web.attributes.Draggable
import org.jetbrains.compose.*
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.alt
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.dom.*


@Composable
fun MyImage() {
    Div {
        //Img(src = "https://www.svgrepo.com/download/380616/hot-coffee-hot-drink.svg", attrs ={
        Img(src = "https://blog.jetbrains.com/wp-content/uploads/2021/05/jetpack-2x.png", attrs = {
            this.style { width(100.px); height(100.px); alt("My Image") }
        })
    }
    Div {
        Svg {

            this.Path {

            }
        }
    }
}
@Composable
fun TestButton(){
    var isOn by remember { mutableStateOf(false) }
    Button(attrs = { classes(if(isOn) "sideBarIconOn" else "sideBarIconOff" ); onClick { isOn = !isOn } }) {

    }
}
@Composable
fun Drag(){
    var height by remember{mutableStateOf(0)}
    Div(attrs = { draggable(Draggable.True); classes("sideBarIcon")}) {
        Text("hello")
    }

}
@Composable
fun SideBar() {
    Div(attrs = { classes("sideBar") }) {
        var n by remember { mutableStateOf(4) }
/*
        SideBarIcon(click = { n++ }) {
            Text("+")
        }

        (1..n).forEach {
            SideBarIcon { Text("$it") }
        }
        */
        //Div { TestButton() }
        //Div { TestButton() }
        Drag()

    }
}

@Composable
fun SideBarIcon(click: () -> Unit = {},
                content: @Composable () -> Unit, ) {
    Div(attrs = {classes("sideBarIcon"); onClick { click() }; contentEditable(false) }) {
        content()
    }
}

@Composable
fun Test() {
    var myList by mutableStateOf(listOf<String>())
    var textState by mutableStateOf("hello")
    Div({ style { padding(10.px) } }) {

        Text(textState)
        Button(attrs = {
            onClick { myList += textState }
        }) {
            Text("+")
        }
        Input(InputType.Text) {
            onInput { update -> textState = update.value }
        }
        myList.forEach { text ->
            Div(attrs = {
                classes(
                    "text-green-500",
                    "font-bold"
                )
            }) {
                Text(text)
            }
        }
    }
}