package pro.atabakov.pro.atabakov.worddistance

import kotlin.math.abs

class WordDistance(words: List<String>) {

    private val indexMap: Map<String, MutableList<Int>>

    init {
        val map = mutableMapOf<String, MutableList<Int>>()
        for (i in words.indices) {
            map.computeIfAbsent(words[i]) { mutableListOf() }.add(i)
        }
        indexMap = map
    }

    fun shortest(word1: String, word2: String): Pair<Int, Int> {
        val list1 = indexMap[word1] ?: return -1 to -1
        val list2 = indexMap[word2] ?: return -1 to -1

        var i = 0
        var j = 0
        var minDistance = Int.MAX_VALUE

        var bestPositions = -1 to -1

        while (i < list1.size && j < list2.size) {
            val pos1 = list1[i]
            val pos2 = list2[j]

            val distance = abs(pos1 - pos2)
            if (distance < minDistance) {
                bestPositions = pos1 to pos2
                minDistance = distance
            }

            if (pos1 < pos2) {
                i++
            } else {
                j++
            }
        }

        return bestPositions
    }
}

fun main() {
    val words = listOf(
        "the", "quick", "brown", "fox",
        "quick", "the", "lazy", "dog"
    )

    val wordDistance = WordDistance(words)

    println(wordDistance.shortest("the", "fox"))
    println(wordDistance.shortest("quick", "dog"))
    println(wordDistance.shortest("the", "quick"))
    println(wordDistance.shortest("brown", "lazy"))
}