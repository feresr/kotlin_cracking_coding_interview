/**
 * Created by feresr on 22/6/17.
 */
class Node(val value: Int) {
    var next: Node? = null

    fun show() {
        print(" $value ")
        next?.show()
    }
}