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

import org.jetbrains.compose.web.attributes.alt
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px

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



/*
fun Node.sayHello() {
    append {
        div {
            classes = setOf("text-green-500")
            +"Hello from JS"
        }
    }
}*/
