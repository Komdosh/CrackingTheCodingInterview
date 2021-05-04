import kotlin.math.abs
import kotlin.test.assertEquals

/***
 * There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character.
 * Given two strings, write a function to check if they are one edit (or zero edits) away.
 */
fun main() {
    assertEquals(true, naiveOneWay("pale", "ple"))
    assertEquals(true, naiveOneWay("pales", "pale"))
    assertEquals(true, naiveOneWay("pale", "bale"))
    assertEquals(false, naiveOneWay("pale", "bake"))

    assertEquals(true, alternativeNaiveOneWay("pale", "ple"))
    assertEquals(true, alternativeNaiveOneWay("pales", "pale"))
    assertEquals(true, alternativeNaiveOneWay("pale", "bale"))
    assertEquals(false, alternativeNaiveOneWay("pale", "bake"))
}

enum class SKIP {
    FIRST, SECOND, BOTH
}

fun naiveOneWay(first: String, second: String): Boolean {
    val lenDif = first.length - second.length
    if (abs(lenDif) > 1) {
        return false
    }
    val availableSkip = when (lenDif) {
        1 -> SKIP.FIRST
        -1 -> SKIP.SECOND
        else -> SKIP.BOTH
    }

    var skipUsed = false
    var firstIndex = 0
    var secondIndex = 0
    while (firstIndex < first.length && secondIndex < second.length) {

        if (first[firstIndex] != second[secondIndex]) {

            if (!skipUsed) {

                when (availableSkip) {
                    SKIP.BOTH -> {
                        ++firstIndex
                        ++secondIndex
                    }
                    SKIP.FIRST -> ++firstIndex
                    SKIP.SECOND -> ++secondIndex
                }

                skipUsed = true

            } else {
                return false
            }

        } else {
            ++firstIndex
            ++secondIndex
        }
    }

    return true
}


fun alternativeNaiveOneWay(first: String, second: String): Boolean {
    return when {
        first.length == second.length -> {
            oneEditReplace(first, second)
        }
        first.length + 1 == second.length -> {
            oneEditInsert(first, second)
        }
        first.length - 1 == second.length -> {
            oneEditInsert(second, first)
        }
        else -> false
    }
}

fun oneEditReplace(first: String, second: String): Boolean {
    var foundDifference = false
    var i = 0
    while (i < first.length) {
        if (first[i] != second[i]) {
            if (foundDifference) {
                return false
            }
        }

        foundDifference = true
        ++i
    }

    return true
}

fun oneEditInsert(first: String, second: String): Boolean {
    var firstIndex = 0
    var secondIndex = 0

    while (secondIndex < second.length && firstIndex < first.length) {
        if(first[firstIndex] != second[secondIndex]){
            if(firstIndex != secondIndex) {
                return false
            }

            ++secondIndex
        } else{
            ++firstIndex
            ++secondIndex
        }
    }
    return true
}
