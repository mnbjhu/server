import org.jetbrains.compose.web.renderComposable

fun main() {
    kotlinext.js.require("./app.css")
    renderComposable(rootElementId = "root") {

    }
}
