package pro.atabakov.pro.atabakov.respace

fun reSpace(dictionary: Set<String>, document: String): Pair<String, Int> {
    val length = document.length
    val dp = IntArray(length + 1) { Int.MAX_VALUE }
    val next = IntArray(length + 1) { -1 }

    dp[length] = 0

    for (i in length - 1 downTo 0) {
        var current = ""
        for (j in i until length) {
            current += document[j]
            val unrecognized = if (dictionary.contains(current)) 0 else current.length
            if (dp[j + 1] != Int.MAX_VALUE && dp[i] > unrecognized + dp[j + 1]) {
                dp[i] = unrecognized + dp[j + 1]
                next[i] = j + 1
            }
        }
    }

    val result = StringBuilder()
    var i = 0
    while (i < length) {
        val j = next[i]
        val word = document.substring(i, j)
        result.append(word)
        result.append(" ")
        i = j
    }

    return Pair(result.toString(), dp[0])
}

fun main() {
    val dictionary = setOf(
        "looked", "just", "like", "her", "brother"
    )
    val document = "jesslookedjustliketimherbrother"

    val (sentence, unrecognized) = reSpace(dictionary, document)

    println(sentence)
    println("($unrecognized unrecognized characters)")
}
