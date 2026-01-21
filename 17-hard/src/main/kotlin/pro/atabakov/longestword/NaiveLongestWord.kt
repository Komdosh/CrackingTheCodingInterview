package pro.atabakov.pro.atabakov.longestword

fun backtrack(remaining: String, memo: MutableMap<String, Boolean>, wordSet: Set<String>): Boolean {
    if (remaining.isEmpty()) return true

    val existing = memo[remaining]
    if (existing != null) return existing

    for (i in 1..remaining.length) {
        val prefix = remaining.substring(0, i)
        val suffix = remaining.substring(i)

        if (wordSet.contains(prefix)) {
            if (backtrack(suffix, memo, wordSet)) {
                memo[remaining] = true
                return true
            }
        }
    }

    memo[remaining] = false
    return false
}

fun longestWord(words: List<String>): String {
    val wordSet = words.toMutableSet()

    val sortedWords = words.sortedByDescending(String::length)

    val memo: MutableMap<String, Boolean> = hashMapOf()

    for (word in sortedWords) {
        wordSet.remove(word)
        memo.clear()

        if (backtrack(word, memo, wordSet)) {
            return word
        }

        wordSet.add(word)
    }

    return ""
}

fun main() {
    val input = listOf("cat", "banana", "dog", "nana", "walk", "walker", "dogwalker")
    println(longestWord(input)) // dogwalker
}