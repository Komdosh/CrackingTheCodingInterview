package pro.atabakov.pro.atabakov.binode

class BiNode(
    var data: Int,
    var node1: BiNode? = null,
    var node2: BiNode? = null
)

class BSTToDoublyLinkedList {
    private var prev: BiNode? = null
    private var head: BiNode? = null

    fun toDoubleLinkedList(root: BiNode?): BiNode? {
        inorder(root)
        return head
    }

    private fun inorder(node: BiNode?) {
        if (node == null) return

        inorder(node.node1)

        val currentPrev = prev
        if (currentPrev == null) {
            head = node
        } else {
            currentPrev.node2 = node
            node.node1 = currentPrev
        }
        prev = node

        inorder(node.node2)
    }
}


