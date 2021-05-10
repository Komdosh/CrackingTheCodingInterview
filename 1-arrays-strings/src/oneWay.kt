import kotlin.math.abs
import kotlin.test.assertEquals

/***
 * One Way: There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character.
 * Given two strings, write a function to check if they are one edit (or zero edits) away.
 */
fun main() {
    assertEquals(true, naiveOneWay("pale", "ple"))
    assertEquals(true, naiveOneWay("pales", "pale"))
    assertEquals(true, naiveOneWay("pale", "bale"))
    assertEquals(false, naiveOneWay("pale", "bake"))
    assertEquals(false, naiveOneWay("ppale", "bpake"))

    assertEquals(true, alternativeNaiveOneWay("pale", "ple"))
    assertEquals(true, alternativeNaiveOneWay("pales", "pale"))
    assertEquals(true, alternativeNaiveOneWay("pale", "bale"))
    assertEquals(false, alternativeNaiveOneWay("pale", "bake"))
    assertEquals(false, alternativeNaiveOneWay("ppale", "bpake"))

    assertEquals(true, optimizedOneWay("pale", "ple"))
    assertEquals(true, optimizedOneWay("pales", "pale"))
    assertEquals(true, optimizedOneWay("pale", "bale"))
    assertEquals(false, optimizedOneWay("pale", "bake"))
    assertEquals(false, optimizedOneWay("ppale", "bpake"))

    assertEquals(true, oneWayKotlinWay("pale", "ple"))
    assertEquals(true, oneWayKotlinWay("pales", "pale"))
    assertEquals(true, oneWayKotlinWay("pale", "bale"))
    assertEquals(false, oneWayKotlinWay("pale", "bake"))
    assertEquals(false, oneWayKotlinWay("ppale", "bpake"))
}

enum class SKIP {
    FIRST, SECOND, BOTH
}

/***
 * Check that second string can be reached by first string by only one edit.
 *
 * We need to compare two strings with the assumption that there can only be one edit: Insert, Delete, Replace on the first or second
 * string, so we can skip one non-equal character.
 *
 * @param first - first string, that should be tested
 * @param second - second string for test
 * @return true if strings have only one edit, false otherwise
 */
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
            if (skipUsed) {
                return false
            }
            skipUsed = true

            when (availableSkip) {
                SKIP.BOTH -> {
                    ++firstIndex
                    ++secondIndex
                }
                SKIP.FIRST -> ++firstIndex
                SKIP.SECOND -> ++secondIndex
            }
        } else {
            ++firstIndex
            ++secondIndex
        }
    }

    return true
}

/***
 * Check that second string can be reached by first string by only one edit.
 *
 * If first string length equal to second string length, then we need to find exactly one symbol replace.
 * If first string shorter than second one, so we need to check that all symbols are in correct positions except one missing for first.
 * If second string shorter than first one, so we need to check that all symbols are in correct positions except one missing for second.
 *
 * @param first - first string, that should be tested
 * @param second - second string for test
 * @return true if strings have only one edit, false otherwise
 */
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

/***
 * Check that second string can be reached by first string by only one replace.
 *
 * @param first - first string
 * @param second - second string
 * @return true if strings have only one replace, false otherwise
 */
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

/***
 * Check that second string can be reached by first string by only one insert.
 *
 * @param first - first string
 * @param second - second string
 * @return true if strings have only one insert, false otherwise
 */
fun oneEditInsert(first: String, second: String): Boolean {
    var firstIndex = 0
    var secondIndex = 0

    while (secondIndex < second.length && firstIndex < first.length) {
        if (first[firstIndex] != second[secondIndex]) {
            if (firstIndex != secondIndex) {
                return false
            }

            ++secondIndex
        } else {
            ++firstIndex
            ++secondIndex
        }
    }
    return true
}

/***
 * Check that second string can be reached by first string by only one edit.
 *
 * We have boolean flag that indicate exact one difference, if it is set twice, then we can't reach second string from first with just
 * one edit.
 *
 * @param first - first string, that should be tested
 * @param second - second string for test
 * @return true if strings have only one edit, false otherwise
 */
fun optimizedOneWay(first: String, second: String): Boolean {
    if (abs(first.length - second.length) > 1) {
        return false
    }

    val smallestStr = if (first.length < second.length) first else second
    val biggestStr = if (first.length < second.length) second else first

    var foundDifference = false
    var firstIndex = 0
    var secondIndex = 0
    while (firstIndex < first.length && secondIndex < second.length) {

        if (smallestStr[firstIndex] != biggestStr[secondIndex]) {
            if (foundDifference) {
                return false
            }
            foundDifference = true

            if (smallestStr.length == biggestStr.length) {
                ++firstIndex
            }
        } else {
            ++firstIndex
        }
        ++secondIndex
    }

    return true
}

/***
 * Check that second string can be reached by first string by only one edit.
 *
 * Count all symbols and count difference, if there is more than one count difference, then it can't be reached.
 *
 * @param first - first string, that should be tested
 * @param second - second string for test
 * @return true if strings have only one edit, false otherwise
 */
fun oneWayKotlinWay(first: String, second: String): Boolean {
    val secondChars = second.toCharArray().toTypedArray().groupingBy { it }.eachCount()
    val firstChars = first.toCharArray().toTypedArray().groupingBy { it }.eachCount()

    var counter = 0

    return firstChars.all {
        val difference = abs((secondChars[it.key] ?: 0) - it.value)
        if (difference > 1) {
            return@all false
        } else if (difference == 1) {
            ++counter
        }
        if (counter > 1) {
            return@all false
        }

        return@all true
    }
}
