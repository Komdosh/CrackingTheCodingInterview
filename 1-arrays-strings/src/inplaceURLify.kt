import kotlin.test.assertEquals


/***
 * URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string
 * has sufficient space at the end to hold the additional characters, and that you are given the "true"
 * length of the string. (Note: If implementing in Java, please use a character array so that you can
 * perform this operation in place.)
 */
fun main() {
    val testString = "Mr John Smith"
    val chars = getStringArray(testString)
    val testString2 = "Mr John Smith "
    val chars2 = getStringArray(testString2)
    val testString3 = " Mr John Smith"
    val chars3 = getStringArray(testString3)
    assertEquals("Mr%20John%20Smith", String(inplaceURlify(chars, testString.length)))
    assertEquals("Mr%20John%20Smith%20", String(inplaceURlify(chars2, testString2.length)))
    assertEquals("%20Mr%20John%20Smith", String(inplaceURlify(chars3, testString3.length)))

    assertEquals("Mr%20John%20Smith", URlifyKotlinWay(testString))
    assertEquals("Mr%20John%20Smith%20", URlifyKotlinWay(testString2))
    assertEquals("%20Mr%20John%20Smith", URlifyKotlinWay(testString3))
}

fun getStringArray(str: String): CharArray {

    val whitespacesCount = str.asSequence().filter { it.isWhitespace() }.count()

    val destination = CharArray(str.length + whitespacesCount * 2) { ' ' }
    str.toCharArray(destination)

    return destination
}

/***
 * Replace whitespaces with %20.
 *
 * Start from end, when whitespace found shift words to the right on two symbols, replace found whitespace with %20.
 *
 * Assumptions:
 *  - We need inplace replace
 *  - Array size already fit for replaced string
 *
 * @param chars - char array with calculated size
 * @param length - length of the original string
 * @return edited char array with replaced whitespaces
 */
fun inplaceURlify(chars: CharArray, length: Int): CharArray {
    var whitespaceCounter = 0
    for (i in IntRange(0, length - 1)) {
        if (chars[i] == ' ') {
            ++whitespaceCounter
        }
    }
    if (whitespaceCounter == 0) {
        return chars
    }

    val replacedBy = "%20".toCharArray()
    var newLength = length + whitespaceCounter * 2 - 1
    var i = length - 1
    while (i >= 0) {
        if (chars[i] == ' ') {
            for (j in IntRange(0, replacedBy.size - 1).reversed()) {
                chars[newLength - j] = replacedBy[replacedBy.size - j - 1]
            }
            newLength -= replacedBy.size
        } else {
            chars[newLength] = chars[i]
            newLength -= 1
        }
        --i
    }
    return chars
}

/***
 * Replace whitespaces with %20.
 *
 * Using standard kotlin replace function.
 *
 * @param str - string with whitespaces
 * @return new string with replaced whitespaces
 */
fun URlifyKotlinWay(str: String): String = str.replace(" ", "%20")
