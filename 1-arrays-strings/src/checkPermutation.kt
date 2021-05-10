import kotlin.test.assertEquals


/***
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the other
 */
fun main() {
    assertEquals(false, naiveCheckPermutation("cat", "fat"))
    assertEquals(true, naiveCheckPermutation("pat", "tap"))

    assertEquals(false, optimizedCheckPermutation("cat", "fat"))
    assertEquals(true, optimizedCheckPermutation("pat", "tap"))

    assertEquals(false, clearCheckPermutation("pat", "fat"))
    assertEquals(true, clearCheckPermutation("pat", "tap"))

    assertEquals(false, checkPermutationKotlinWay("pat", "fat"))
    assertEquals(true, checkPermutationKotlinWay("pat", "tap"))
}

/***
 * Check two strings for permutation
 *
 *
 *
 * Assumptions:
 *  - String case sensitive
 *  - Whitespace is countable
 *
 * @param first - first string
 * @param second - second string
 * @return true if first string is permutation of second, false otherwise
 */
fun naiveCheckPermutation(first: String, second: String): Boolean {
    if (first.length != second.length) {
        return false
    }

    val counter = HashMap<Int, Int>()

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = first[i].code

        counter[symbolCode] = (counter[symbolCode] ?: 0) + 1
    }

    val secondCounter = HashMap<Int, Int>()

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = second[i].code

        secondCounter[symbolCode] = (secondCounter[symbolCode] ?: 0) + 1
    }

    for ((key, value) in counter) {
        if (secondCounter[key] != value) {
            return false
        }
    }

    return true
}

/***
 * Check two strings for permutation
 *
 * Assumptions:
 * - String case sensitive
 * - Whitespace is countable
 *
 * @param first - first string
 * @param second - second string
 * @return true if first string is permutation of second, false otherwise
 */
fun optimizedCheckPermutation(first: String, second: String): Boolean {
    if (first.length != second.length) {
        return false
    }

    val counter = HashMap<Int, Int>()

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = first[i].code

        counter[symbolCode] = (counter[symbolCode] ?: 0) + 1
    }

    for (i in IntRange(0, first.length - 1)) {
        val symbolCode = second[i].code

        if ((counter[symbolCode] ?: 0) == 0) {
            return false
        }
        counter[symbolCode] = counter[symbolCode]!! - 1
    }

    return true
}

/***
 * Check two strings for permutation
 *
 * Sort:
 * - String case sensitive
 * - Whitespace is countable
 *
 * @param first - first string
 * @param second - second string
 * @return true if first string is permutation of second, false otherwise
 */
fun clearCheckPermutation(first: String, second: String): Boolean {
    if (first.length != second.length) {
        return false
    }

    val firstSorted = first.toCharArray()
    val secondSorted = second.toCharArray()
    firstSorted.sort()
    secondSorted.sort()

    for (i in IntRange(0, first.length - 1)) {
        if (firstSorted[i] != secondSorted[i]) {
            return false
        }
    }

    return true
}

/***
 * Check two strings for permutation
 *
 * @param first - first string
 * @param second - second string
 * @return true if first string is permutation of second, false otherwise
 */
fun checkPermutationKotlinWay(first: String, second: String): Boolean = first.toCharArray().toSet() == second.toCharArray().toSet()
