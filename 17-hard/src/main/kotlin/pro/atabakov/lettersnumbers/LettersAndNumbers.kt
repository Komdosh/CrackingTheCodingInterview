package pro.atabakov.pro.atabakov.lettersnumbers

fun longestBalancedSubarray(arr: Array<Char>): Pair<Int, Int> {
    val deltas = Array(arr.size) { -1 }

    var prefixSum = 0
    var maxLen = 0
    var result: Pair<Int, Int> = Pair(0, 0)

    for (i in arr.indices) {
        if (arr[i].isLetter()) ++prefixSum else --prefixSum

        val currentDelta = deltas[prefixSum]
        if (currentDelta != -1) {
            val start = currentDelta + 1
            val length = i - currentDelta
            if (length > maxLen) {
                maxLen = length
                result = Pair(start, i)
            }
        } else {
            deltas[prefixSum] = i
        }
    }

    return result
}

fun main() {
    val arr =
        arrayOf('a', 'a', 'a', 'a', '1', '1', 'a', '1', '1', 'a', 'a', '1', 'a', 'a', '1', 'a', 'a', 'a', 'a', 'a')

    val (start, end) = longestBalancedSubarray(arr)

    println("Start: $start, End: $end")
    if (end > 0) {
        println(arr.slice(start..end))
    } else println("Empty!")
}
