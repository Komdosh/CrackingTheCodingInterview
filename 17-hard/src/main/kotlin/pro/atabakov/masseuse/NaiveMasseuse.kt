package pro.atabakov.pro.atabakov.masseuse

fun maxBookedMinutesWithSequence(requests: IntArray): Pair<Int, List<Int>> {
    val n = requests.size
    if (n == 0) return 0 to emptyList()
    if (n == 1) return requests[0] to requests.take(n)

    val dp = IntArray(n)
    val take = BooleanArray(n)

    dp[0] = requests[0]
    take[0] = true

    if (requests[1] > requests[0]) {
        dp[1] = requests[1]
        take[1] = true
    } else {
        dp[1] = requests[0]
        take[1] = false
    }

    for (i in 2 until n) {
        val takeValue = dp[i - 2] + requests[i]
        val skipValue = dp[i - 1]

        if (takeValue > skipValue) {
            dp[i] = takeValue
            take[i] = true
        } else {
            dp[i] = skipValue
        }
    }

    val sequence = ArrayList<Int>()
    var i = n - 1
    while (i >= 0) {
        if (take[i]) {
            sequence.add(requests[i])
            i--
        }

        i--
    }

    sequence.reverse()
    return dp[n - 1] to sequence
}

fun main() {
    val input = intArrayOf(30, 15, 60, 75, 45, 15, 15, 45)
    val (minutes, sequence) = maxBookedMinutesWithSequence(input)

    println("$minutes minutes (${sequence})")
}
