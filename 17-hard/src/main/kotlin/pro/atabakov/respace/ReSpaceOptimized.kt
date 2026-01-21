package pro.atabakov.pro.atabakov.respace

data class ParseResult(
    var invalid: Int = Int.MAX_VALUE,
    var parsed: String = ""
)

fun bestSplit(dictionary: Set<String>, sentence: String): String {
    val memo = arrayOfNulls<ParseResult>(sentence.length)
    val result = split(dictionary, sentence, 0, memo)
    return result.parsed
}

fun split(
    dictionary: Set<String>,
    sentence: String,
    start: Int,
    memo: Array<ParseResult?>
): ParseResult {

    if (start >= sentence.length) {
        return ParseResult(0, "")
    }

    memo[start]?.let { return it }

    var bestInvalid = Int.MAX_VALUE
    var bestParsing: String? = null
    val partial = StringBuilder()

    var index = start
    while (index < sentence.length) {
        partial.append(sentence[index])
        val word = partial.toString()

        val invalid = if (dictionary.contains(word)) 0 else word.length

        // Optimization: only continue if it can improve
        if (invalid < bestInvalid) {
            val result = split(dictionary, sentence, index + 1, memo)

            if (invalid + result.invalid < bestInvalid) {
                bestInvalid = invalid + result.invalid
                bestParsing = word + " " + result.parsed

                // Optimization: perfect match
                if (bestInvalid == 0) break
            }
        }
        index++
    }

    val finalResult = ParseResult(bestInvalid, bestParsing ?: "")
    memo[start] = finalResult
    return finalResult
}

fun main() {
    val dictionary = hashSetOf(
        "looked", "just", "like", "her", "brother"
    )

    val sentence = "jesslookedjustliketimherbrother"
    val result = bestSplit(dictionary, sentence)

    println(result)
}
