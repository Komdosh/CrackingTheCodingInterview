package pro.atabakov.pro.atabakov.longestword

fun printLongestWord(arr: Array<String>): String {
    val map = mutableMapOf<String, Boolean>()

    // Put all words in the map
    for (str in arr) {
        map[str] = true
    }

    // Sort by descending length
    arr.sortByDescending(String::length)

    for (s in arr) {
        if (canBuildWord(s, true, map)) {
            println(s)
            return s
        }
    }

    return ""
}

fun canBuildWord(
    str: String,
    isOriginalWord: Boolean,
    map: MutableMap<String, Boolean>
): Boolean {

    if (map.containsKey(str) && !isOriginalWord) {
        return map[str] == true
    }

    for (i in 1 until str.length) {
        val left = str.substring(0, i)
        val right = str.substring(i)

        if (map[left] == true && canBuildWord(right, false, map)) {
            return true
        }
    }

    map[str] = false
    return false
}

fun main() {
    val input = arrayOf("cat", "banana", "dog", "nana", "walk", "walker", "dogwalker")
    printLongestWord(input) // dogwalker
}
