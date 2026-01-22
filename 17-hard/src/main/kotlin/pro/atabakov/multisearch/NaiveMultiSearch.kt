package pro.atabakov.pro.atabakov.multisearch

data class TrieNodeNaive(
    val children: MutableMap<Char, TrieNodeNaive> = mutableMapOf(),
    var word: String? = null
)

fun buildTrie(words: Array<String>): TrieNodeNaive {
    val root = TrieNodeNaive()

    for (word in words) {
        var node = root
        for (c in word) {
            node = node.children.getOrPut(c) { TrieNodeNaive() }
        }
        node.word = word
    }
    return root
}

fun searchAll(b: String, words: Array<String>): Set<String> {
    val root = buildTrie(words)
    val found = mutableSetOf<String>()

    for (i in b.indices) {
        var node = root
        var j = i

        while (j < b.length) {
            val c = b[j]
            node = node.children[c] ?: break

            node.word?.let(found::add)

            j++
        }
    }
    return found
}

fun main() {
    val b = "mississippi"
    val T = arrayOf("is", "ppi", "hi", "sis", "i", "ssippi")

    val result = searchAll(b, T)
    println(result)
}

