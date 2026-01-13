package pro.atabakov.pro.atabakov.circus

fun longestTower(people: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    if (people.isEmpty()) return emptyList()

    val sorted = people.sortedWith(
        compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
    )

    val n = sorted.size
    val dp = IntArray(n) { 1 }
    val prev = IntArray(n) { -1 }

    var maxIndex = 0

    for (i in 0 until n) {
        for (j in 0 until i) {
            if (sorted[j].second < sorted[i].second && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1
                prev[i] = j
            }
        }
        if (dp[i] > dp[maxIndex]) {
            maxIndex = i
        }
    }

    val result = mutableListOf<Pair<Int, Int>>()
    var curr = maxIndex
    while (curr != -1) {
        result.add(sorted[curr])
        curr = prev[curr]
    }

    return result.reversed()
}