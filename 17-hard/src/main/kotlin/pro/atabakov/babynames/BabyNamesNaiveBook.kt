package pro.atabakov.pro.atabakov.babynames

class NameSet(name: String, freq: Int) {

    private val names: MutableSet<String> = mutableSetOf()
    var frequency: Int = 0
        private set
    var rootName: String
        private set

    init {
        names.add(name)
        frequency = freq
        rootName = name
    }

    fun copyNamesWithFrequency(more: Set<String>, freq: Int) {
        names.addAll(more)
        frequency += freq
    }

    fun getNames(): Set<String> = names
    fun size(): Int = names.size
}

fun convertToMap(
    groups: MutableMap<String, NameSet>
): Map<String, Int> {

    val result = mutableMapOf<String, Int>()

    for (group in groups.values) {
        result[group.rootName] = group.frequency
    }

    return result
}

fun constructGroups(
    names: Map<String, Int>
): MutableMap<String, NameSet> {

    val groups = mutableMapOf<String, NameSet>()

    for ((name, frequency) in names) {
        val group = NameSet(name, frequency)
        groups[name] = group
    }

    return groups
}

fun mergeClasses(
    groups: MutableMap<String, NameSet>,
    synonyms: Array<Pair<String, String>>
) {
    for ((name1, name2) in synonyms) {
        val set1 = groups[name1]
        val set2 = groups[name2]

        if (set1 != null && set2 != null && set1 !== set2) {
            val smaller = if (set2.size() < set1.size()) set2 else set1
            val bigger = if (set2.size() < set1.size()) set1 else set2
            val otherNames = smaller.getNames()
            val frequency = smaller.frequency
            bigger.copyNamesWithFrequency(otherNames, frequency)

            for (name in otherNames) {
                groups[name] = bigger
            }
        }
    }
}


fun main() {
    val names = mapOf(
        "John" to 15,
        "Jon" to 12,
        "Chris" to 13,
        "Kris" to 4,
        "Christopher" to 19
    )

    val synonyms = arrayOf(
        "Jon" to "John",
        "John" to "Johnny",
        "Chris" to "Kris",
        "Chris" to "Christopher",
    )

    val groups = constructGroups(names)

    mergeClasses(groups, synonyms)

    val result = convertToMap(groups)

    println(result)
}