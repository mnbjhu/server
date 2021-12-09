import androidx.compose.runtime.*
import kotlinx.html.div
import kotlinx.html.dom.append
import org.w3c.dom.Node
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CompletionHandler
import kotlinx.html.classes
import org.jetbrains.compose.web.attributes.Draggable
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.Options
import org.jetbrains.compose.web.attributes.alt
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.selectors.attr
import org.jetbrains.compose.web.css.selectors.className
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {
    kotlinext.js.require("./app.css")
    renderComposable(rootElementId = "root") {

        //MyImage()
        //SideBar()
        //DragAndDropList()
        TestList()


    }
}

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

/*
fun Node.sayHello() {
    append {
        div {
            classes = setOf("text-green-500")
            +"Hello from JS"
        }
    }
}*/
