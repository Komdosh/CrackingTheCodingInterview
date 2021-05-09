import kotlin.test.assertEquals

/***
 * String Rotation:
 * Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1 and s2,
 * write code to check if s2 is a rotation of s1 using only one call to isSubstring (e.g., `waterbottle` is a rotation of `erbottlewat`).
 */
fun main() {
    assertEquals(true, naiveIsStringRotation("waterbottle", "erbottlewat"))
    assertEquals(true, optimizedIsStringRotation("waterbottle", "erbottlewat"))

    assertEquals(false, naiveIsStringRotation("waterbottl", "erbottlewat"))
    assertEquals(false, optimizedIsStringRotation("waterbottl", "erbottlewat"))

    assertEquals(false, naiveIsStringRotation("waterbottlee", "erbottlewat"))
    assertEquals(false, optimizedIsStringRotation("waterbottlee", "erbottlewat"))

    assertEquals(false, naiveIsStringRotation("waterbottle", "erbottlewet"))
    assertEquals(false, optimizedIsStringRotation("waterbottle", "erbottlewet"))
}

/***
 * Check if first string is string rotation of second or vice versa.
 *
 * we can compare symbols in parallel, if second a part of first, it should have non zero equal char sequence,
 * then we just compare remainder start symbols of first, and last symbols of second
 *
 * @param first - first string
 * @param second - second string
 * @return true if first string is rotation of second, false otherwise
 */
fun naiveIsStringRotation(first: String, second: String): Boolean {
    if (first.length != second.length || first.isEmpty() || second.isEmpty()) {
        return false
    }
    var i = 0
    var j = 0
    var startSequence = -1
    while (i < second.length) {
        if(first[i] == second[j]){
            ++j
            if(startSequence==-1){
                startSequence = i
            }
        }  else if(startSequence!= -1){
            startSequence = -1
        }
        ++i
    }
    if(startSequence != -1) {
        if(first.substring(0, startSequence) == second.substring(second.length-startSequence, second.length)){
            return true
        }
    }
    return false
}


/***
 * Check if first string is string rotation of second or vice versa.
 *
 * if first string concatenate with itself, then rotated string should be a part of it
 *
 * erbottlewet in (waterbottle + waterbottle)
 *
 * @param first - first string
 * @param second - second string
 * @return true if first string is rotation of second, false otherwise
 */
fun optimizedIsStringRotation(first: String, second: String): Boolean {
    if (first.length == second.length && first.isNotEmpty()) {
        return (first + first).contains(second)
    }
    return false
}
