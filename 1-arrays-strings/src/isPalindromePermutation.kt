import kotlin.test.assertEquals


/***
 * Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word or
 * phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome does not need to be
 * limited to just dictionary words.
 *
 * Assumptions:
 *   - String case insensitive
 */
fun main() {
    assertEquals(false, naiveIsPalindromePermutation("Tast Coa"))
    assertEquals(true, naiveIsPalindromePermutation("Tact Coa"))

    assertEquals(false, alternativeNaiveIsPalindromePermutation("Tast Coa"))
    assertEquals(true, alternativeNaiveIsPalindromePermutation("Tact Coa"))

    assertEquals(false, optimizedIsPalindromePermutation("Tast Coa"))
    assertEquals(true, optimizedIsPalindromePermutation("Tact Coa"))

    assertEquals(false, isPalindromePermutationKotlinWay("Tast Coa"))
    assertEquals(true, isPalindromePermutationKotlinWay("Tact Coa"))
}

/***
 * @property testStr - tested string
 * @return true if first string is permutation of palindrome, false otherwise
 */
fun naiveIsPalindromePermutation(testStr: String): Boolean {
    val preparedTestString = testStr.toLowerCase().filter { it != ' ' }.toCharArray()
    val isStrLenEven = preparedTestString.size % 2 == 0
    val charsMap = HashMap<Char, Int>()
    preparedTestString.forEach {
        charsMap[it] = (charsMap[it] ?: 0) + 1
    }

    var hasOddNumberOfChars = false
    for (value in charsMap.values) {
        if (value % 2 != 0) {
            if (hasOddNumberOfChars || isStrLenEven) {
                return false
            }
            hasOddNumberOfChars = true
        }
    }
    return true
}

/***
 * @property testStr - tested string
 * @return true if first string is permutation of palindrome, false otherwise
 */
fun alternativeNaiveIsPalindromePermutation(testStr: String): Boolean {
    val charsMap = HashMap<Char, Int>()
    var countOdd = 0
    testStr.toLowerCase().toCharArray().filter { it != ' ' }.forEach {
        charsMap[it] = (charsMap[it] ?: 0) + 1
        if (charsMap[it]!! % 2 == 1) {
            ++countOdd
        } else {
            --countOdd
        }
    }

    return countOdd <= 1
}

/***
 * Assumptions:
 *  - String contains only a-z symbols
 * @property testStr - tested string
 * @return true if first string is permutation of palindrome, false otherwise
 */
fun optimizedIsPalindromePermutation(testStr: String): Boolean {
    val bitVector = createBitVector(testStr)
    return bitVector == 0 || checkExactlyOneBitSet(bitVector)
}

/***
 * Set i symbol of bit vector, which represents index of symbol in alphabet
 *
 * @property testStr - tested string
 * @return bit vector
 */
fun createBitVector(testStr: String): Int {
    var bitVector = 0
    for (char in testStr.toCharArray()) {
        val x = getCharNumber(char)
        bitVector = toggle(bitVector, x)
    }
    return bitVector
}

fun getCharNumber(char: Char): Int {
    return char.toLowerCase().toInt() - 'a'.toInt()
}

fun toggle(bitVector: Int, index: Int): Int {
    if (index < 0) return bitVector

    val mask = 1.shl(index)

    return if ((bitVector.and(mask)) == 0) {
        bitVector.or(mask)
    } else {
        bitVector.and(mask.inv())
    }
}

/***
 * Example:
 *   True:
 *      00010000 - 1 = 00001111
 *      00010000 & 00001111 = 0
 *   False:
 *      00010100 - 1 = 00010011
 *      00010100 & 00010011 = 00010000 = 16
 *
 * @property bitVector - bit vector of presented symbols
 * @return true if first string contains only 1 set bit, false otherwise
 */
fun checkExactlyOneBitSet(bitVector: Int): Boolean {
    return bitVector.and(bitVector - 1) == 0
}


/***
 * @property testStr - tested string
 * @return true if first string is permutation of palindrome, false otherwise
 */
fun isPalindromePermutationKotlinWay(testStr: String): Boolean {
    val preparedTestString = testStr.toLowerCase().filter { it != ' ' }.toCharArray()
    val charsMap = preparedTestString.toTypedArray().groupingBy { it }.eachCount()

    return charsMap.values.filter { it % 2 != 0 }.count() <= 1
}
