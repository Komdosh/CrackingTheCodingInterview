package pro.atabakov.pro.atabakov.shortestsuperseq

fun shortestSubarrayContainingAll(shorter: IntArray, longer: IntArray): Pair<Int, Int>? {
    val required = shorter.toSet()
    val countMap = mutableMapOf<Int, Int>()
    var foundSize = 0
    val needSize = required.size

    var left = 0
    var start = -1
    var end = -1
    var currentLength = Int.MAX_VALUE

    for (right in longer.indices) {
        val v = longer[right]
        if (v in required) {
            countMap[v] = (countMap[v] ?: 0) + 1
            if (countMap[v] == 1) foundSize++
        }

        // Try to shrink when valid
        while (foundSize == needSize) {
            if (right - left + 1 < currentLength) {
                currentLength = right - left + 1
                start = left
                end = right
            }

            val lv = longer[left]
            if (lv in required) {
                countMap[lv] = countMap[lv]!! - 1
                if (countMap[lv] == 0) foundSize--
            }
            left++
        }
    }

    return if (start == -1) null else start to end
}

fun main() {
    val shorter = intArrayOf(1, 5, 9)
    val longer = intArrayOf(7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7)

    val res = shortestSubarrayContainingAll(shorter, longer)
    println(res)  // Expected: (7, 10)
}

