import java.lang.StringBuilder
import kotlin.test.assertEquals

/***
 * Implement a method to perform basic string compression using the counts of repeated characters.
 * If the "compressed" string would not become smaller than the original string, your method should return the original string.
 * You can assume the string has only uppercase and lowercase letters (a - z).
 */
fun main() {
    assertEquals("a2b1c5a3", naiveStringCompression("aabcccccaaa"))
    assertEquals("a2b1c5a1", naiveStringCompression("aabccccca"))
    assertEquals("abc", naiveStringCompression("abc"))

    assertEquals("a2b1c5a3", optimizedStringCompression("aabcccccaaa"))
    assertEquals("a2b1c5a1", optimizedStringCompression("aabccccca"))
    assertEquals("abc", optimizedStringCompression("abc"))
}

/***
 * @property toCompression - string to compress
 * @return compressed string if it is shorter, than original
 */
fun naiveStringCompression(toCompression: String): String {
    val length = toCompression.length

    var i = 0
    val stringBuilder = StringBuilder()
    var counter = 1
    while (i < length) {
        val c = toCompression[i]

        if (i + 1 == length || toCompression[i + 1] != c) {
            stringBuilder.append(c)
            stringBuilder.append(counter)
            counter = 1
        } else {
            ++counter
        }

        ++i
    }
    val compressed = stringBuilder.toString()
    return if(compressed.length < toCompression.length) compressed else toCompression
}

/***
 * @property toCompression - string to compress
 * @return compressed string if it is shorter, than original
 */
fun optimizedStringCompression(toCompression: String): String {
    val finalLength = countCompression(toCompression)
    if(finalLength>=toCompression.length){
        return toCompression
    }

    val compressed = StringBuilder(finalLength)
    var countConsecutive = 0
    var i = 0
    while(i<toCompression.length){
        ++countConsecutive
        if(i+1 >= toCompression.length || toCompression[i] != toCompression[i+1]){
            compressed.append(toCompression[i])
            compressed.append(countConsecutive)
            countConsecutive = 0
        }
        ++i
    }
    return compressed.toString()
}

/***
 * Count compression size
 */
fun countCompression(toCompression: String): Int{
    var compressedLength = 0
    var countConsecutive = 0
    var i = 0
    while(i<toCompression.length){
        ++countConsecutive
        if(i+1 >= toCompression.length || toCompression[i] != toCompression[i+1]){
            compressedLength += 1 + compressedLength.toString().length
            countConsecutive = 0
        }
        ++i
    }
    return compressedLength
}
