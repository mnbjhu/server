import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import org.jetbrains.compose.web.attributes.Draggable
import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.*

@Composable
fun DragAndDropList() {
    Div(attrs = {classes("sideBar")}) {
        var items by remember { mutableStateOf(List(5){ it }) }
        var inDrag: Int? by remember { mutableStateOf(null) }
        var draggedInto: Int? by remember { mutableStateOf(null) }
        val swap = { index: Int, value: Int ->
            if(draggedInto == null || inDrag ==null) value
            else {
                when(index){
                    inDrag -> items[draggedInto!!]
                    draggedInto -> items[inDrag!!]
                    else -> value
                }
            }
        }
        items.mapIndexed(swap) .forEach { index ->
            val isInDrag = index == inDrag
            Div(attrs = {
                classes("sideBarIcon", if(!isInDrag) "visible" else "invisible")
                draggable(Draggable.True)
                onDragStart { inDrag = index }
                onDragEnd { inDrag = null }
                if(!isInDrag) {
                    onDragOver { it.preventDefault(); }
                    onDragEnter {
                        it.preventDefault()
                        draggedInto = index
                    }
                    onDragLeave { draggedInto = null }
                    onDrop { items = items.mapIndexed(swap); draggedInto = null; inDrag = null }
                }
            }) {
                Text("Test $index")
            }
        }
    }

}
@Composable
fun TestList(){
    var items by remember { mutableStateOf(listOf("Item")) }
    Button(attrs = { onClick { items += "Another Item" } }) {
        Text("Add")
    }
    Ul(attrs = {
        classes("items")
        items.forEachIndexed { index: Int, s: String ->
            //classes("li:nth-child(${index+1})  { transform: translate3d(, ${index*100}%, ); }")

        }
    }
    ) {
        items.forEach {
            Li(attrs = {classes("items")}) {
                Text(it)
            }

        }
    }
}
