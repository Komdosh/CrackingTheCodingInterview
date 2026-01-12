package pro.atabakov.pro.atabakov.lettersnumbers

fun findLongestSubarray(array: CharArray): CharArray {
    for (len in array.size downTo 2) {
        for (i in 0..array.size - len) {
            val end = i + len - 1
            if (hasEqualLettersNumbers(array, i, end)) {
                return extractSubarray(array, i, end)
            }
        }
    }
    return charArrayOf()
}

fun hasEqualLettersNumbers(
    array: CharArray,
    start: Int,
    end: Int
): Boolean {
    var counter = 0
    for (i in start..end) {
        if (array[i].isLetter()) counter++ else counter--
    }
    return counter == 0
}

fun extractSubarray(
    array: CharArray,
    start: Int,
    end: Int
): CharArray {
    val subarray = CharArray(end - start + 1)
    for (i in start..end) {
        subarray[i - start] = array[i]
    }
    return subarray
}

fun main() {
    val arr =
        charArrayOf('a', 'a', 'a', 'a', '1', '1', 'a', '1', '1', 'a', 'a', '1', 'a', 'a', '1', 'a', 'a', 'a', 'a', 'a')

    val res = findLongestSubarray(arr)
    println(res.joinToString())
}
