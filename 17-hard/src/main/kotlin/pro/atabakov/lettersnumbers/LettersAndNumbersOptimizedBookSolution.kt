package pro.atabakov.pro.atabakov.lettersnumbers

fun findLongestSubarrayOptimized(array: CharArray): CharArray {
    val deltas = computeDeltaArray(array)
    val match = findLongestMatch(deltas)
    return extractSubarray(array, match[0] + 1, match[1])
}

fun findLongestMatch(deltas: IntArray): IntArray {
    val map = mutableMapOf<Int, Int>()
    map[0] = -1

    val max = IntArray(2)

    for (i in deltas.indices) {
        if (!map.containsKey(deltas[i])) {
            map[deltas[i]] = i
        } else {
            val match = map[deltas[i]]!!
            val distance = i - match
            val longest = max[1] - max[0]

            if (distance > longest) {
                max[1] = i
                max[0] = match
            }
        }
    }

    return max
}


fun computeDeltaArray(array: CharArray): IntArray {
    val deltas = IntArray(array.size)
    var delta = 0

    for (i in array.indices) {
        when {
            array[i].isLetter() -> delta++
            array[i].isDigit() -> delta--
        }
        deltas[i] = delta
    }

    return deltas
}


fun main() {
    val arr =
        charArrayOf('a', 'a', 'a', 'a', '1', '1', 'a', '1', '1', 'a', 'a', '1', 'a', 'a', '1', 'a', 'a', 'a', 'a', 'a')

    val res = findLongestSubarrayOptimized(arr)
    println(res.joinToString())
}
