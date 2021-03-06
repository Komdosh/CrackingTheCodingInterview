import kotlin.test.assertEquals


/***
 * Is Unique: Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 */
fun main() {
    assertEquals(false, naiveIsUniqueChars("I have a bottle of water"))
    assertEquals(true, naiveIsUniqueChars("No idea"))

    assertEquals(false, optimizedIsUniqueChars("I have a bottle of water"))
    assertEquals(true, optimizedIsUniqueChars("No idea"))

    assertEquals(false, isUniqueCharsKotlinWay("I have a bottle of water"))
    assertEquals(true, isUniqueCharsKotlinWay("No idea"))
}

/***
 * Check string contains only unique symbols.
 *
 * Store used symbols in array, if array already contains a symbol, then string not contains unique chars.
 *
 * Assumptions:
 *  - String contains only ASCII symbols
 *
 * @param testStr - string that should be tested
 * @return true if string contains only unique chars, false otherwise
 */
fun naiveIsUniqueChars(testStr: String): Boolean {
    if (testStr.length > 128) return false

    val used = Array(128) { false }

    for (i in IntRange(0, testStr.length - 1)) {
        val symbolCode = testStr[i].code
        if (used[symbolCode]) {
            return false
        }
        used[symbolCode] = true
    }
    return true
}

/***
 * Check string contains only unique symbols.
 *
 * Store used symbols in bitVector, if bitVector already contains a symbol, then string not contains unique chars.
 *
 *  Assumptions:
 *  - String contains only a-z symbols
 *
 * @param testStr - string that should be tested
 * @return true if string contains only unique chars, false otherwise
 */
fun optimizedIsUniqueChars(testStr: String): Boolean {
    if (testStr.length > 26) return false

    var a = 0

    for (i in IntRange(0, testStr.length - 1)) {
        val symbolCode = testStr[i].code - 'a'.code
        if ((a and (1 shl symbolCode)) > 0) {
            return false
        }

        a = a or (1 shl symbolCode)
    }
    return true
}

/***
 * Check string contains only unique symbols.
 *
 * Convert string to char set, if size of original string equals to set, then string contains only unique symbols.
 *
 * @param testStr - string that should be tested
 * @return true if string contains only unique chars, false otherwise
 */
fun isUniqueCharsKotlinWay(testStr: String): Boolean =
    testStr.toCharArray().toSet().size == testStr.length

