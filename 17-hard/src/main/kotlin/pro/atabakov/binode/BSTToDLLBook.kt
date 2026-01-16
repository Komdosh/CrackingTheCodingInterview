package pro.atabakov.pro.atabakov.binode


fun convert(root: BiNode?): BiNode? {
    val head = convertToCircular(root) ?: return null

    // Break the circular links
    head.node1!!.node2 = null
    head.node1 = null

    return head
}

private fun convertToCircular(root: BiNode?): BiNode? {
    if (root == null) return null

    val part1 = convertToCircular(root.node1)
    val part3 = convertToCircular(root.node2)

    if (part1 == null && part3 == null) {
        root.node1 = root
        root.node2 = root
        return root
    }

    /* Connect left part with root */
    if (part1 == null) {
        concat(part3!!.node1!!, root)
    } else {
        concat(part1.node1!!, root)
    }

    /* Connect right part with root */
    if (part3 == null) {
        concat(root, part1!!)
    } else {
        concat(root, part3)
    }

    val tail3 = part3?.node1
    /* Connect right part with left part */
    if (part1 != null && part3 != null) {
        concat(tail3!!, part1)
    }

    return part1 ?: root
}

private fun concat(a: BiNode, b: BiNode) {
    a.node2 = b
    b.node1 = a
}