package pro.atabakov.pro.atabakov.multisearch

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
    var terminates = false

    fun getChild(c: Char): TrieNode? = children[c]
}

class Trie {
    val root = TrieNode()

    fun insertString(s: String, index: Int) {
        var node = root
        var i = index

        while (i < s.length) {
            val c = s[i]
            node = node.children.getOrPut(c) { TrieNode() }
            i++
        }
        node.terminates = true
    }
}


fun searchAllTrie(big: String, smalls: Array<String>): MutableMap<String, MutableList<Int>> {
    val lookup = mutableMapOf<String, MutableList<Int>>()
    val maxLen = big.length

    val root = createTreeFromStrings(smalls, maxLen).root

    for (i in big.indices) {
        val strings = findStringsAtLoc(root, big, i)
        insertIntoHashMap(strings, lookup, i)
    }

    return lookup
}

fun createTreeFromStrings(smalls: Array<String>, maxLen: Int): Trie {
    val tree = Trie()

    for (s in smalls) {
        if (s.length <= maxLen) {
            tree.insertString(s, 0)
        }
    }
    return tree
}

fun findStringsAtLoc(root: TrieNode, big: String, start: Int): List<String> {
    val strings = mutableListOf<String>()
    var node: TrieNode? = root
    var index = start

    while (index < big.length && node != null) {
        node = node.getChild(big[index])
        if (node == null) break

        if (node.terminates) {
            strings.add(big.substring(start, index + 1))
        }
        index++
    }
    return strings
}

fun insertIntoHashMap(strings: List<String>, lookup: MutableMap<String, MutableList<Int>>, index: Int) {
    for (s in strings) {
        lookup.computeIfAbsent(s) { mutableListOf() }.add(index)
    }
}


fun main() {
    val b = "mississippi"
    val T = arrayOf("is", "ppi", "hi", "sis", "i", "ssippi")

    val result = searchAllTrie(b, T)
    println(result)
}
