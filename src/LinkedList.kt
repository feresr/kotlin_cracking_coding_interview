/**
 * Created by feresr on 22/6/17.
 */
class LinkedList {

    var head: Node? = null

    fun add(value: Int) {
        val new = Node(value)
        new.next = head
        head = new
    }

    fun show() {
        head?.show()
    }

    fun removeKthToEnd(k: Int): Node? {
        var p1 = head
        var p2 = head

        for (i in 0 until k) {
            p2 = p2?.next
            if (p2 == null) return null
        }

        while (p2?.next != null) {
            p1 = p1?.next
            p2 = p2.next
        }

        if (p1 != null) {
            return remove(p1)
        } else {
            return null
        }
    }

    fun remove(node: Node): Node? {
        if (node == head) {
            head = head?.next
            return node
        } else {
            var focus: Node? = head
            while (focus != null) {
                if (focus.next == node) {
                    focus.next = focus.next?.next
                    return node
                }

                focus = focus.next
            }
        }

        return null;
    }
}