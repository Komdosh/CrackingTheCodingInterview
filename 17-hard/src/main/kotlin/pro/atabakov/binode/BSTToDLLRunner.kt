package pro.atabakov.pro.atabakov.binode

fun main() {
    val root = BiNode(4)
    root.node1 = BiNode(2, BiNode(1), BiNode(3))
    root.node2 = BiNode(5)

    val bstToDll = BSTToDoublyLinkedList()
//    var head = bstToDll.toDoubleLinkedList(root)

    var head = convert(root)

    var tail = head
    println("Forward:")
    while (head != null) {
        print("${head.data} ")
        tail = head
        head = head.node2
    }

    println("\nBackward:")
    while (tail != null) {
        print("${tail.data} ")
        tail = tail.node1
    }
}