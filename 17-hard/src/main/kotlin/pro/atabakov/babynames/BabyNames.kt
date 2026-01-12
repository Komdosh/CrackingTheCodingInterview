package pro.atabakov.pro.atabakov.babynames


class UnionFind {
    private val namesMap = mutableMapOf<String, String>()

    fun find(name: String): String {
        namesMap.putIfAbsent(name, name)
        if (namesMap[name] != name) {
            namesMap[name] = find(namesMap[name]!!)
        }
        return namesMap[name]!!
    }

    fun union(first: String, second: String) {
        val left = find(first)
        val right = find(second)
        if (left != right) {
            namesMap[right] = left
        }
    }
}

fun mergeNamesNaive(
    names: Map<String, Int>,
    synonyms: List<Pair<String, String>>
): Map<String, Int> {
    val unionFind = UnionFind()

    for ((first, second) in synonyms) {
        unionFind.union(first, second)
    }

    val result = mutableMapOf<String, Int>()
    for ((name, freq) in names) {
        val root = unionFind.find(name)
        result[root] = result.getOrDefault(root, 0) + freq
    }

    return result
}

fun main() {
    val result = mergeNamesNaive(
        mapOf(
            "John" to 15,
            "Jon" to 12,
            "Chris" to 13,
            "Kris" to 4,
            "Christopher" to 19
        ),
        listOf(
            "Jon" to "John",
            "John" to "Johnny",
            "Chris" to "Kris",
            "Chris" to "Christopher",
        )
    )

    println(result)
}