package pro.atabakov.pro.atabakov.circus

fun longestTowerTails(people: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    if (people.isEmpty()) return emptyList()

    val sorted = people.sortedWith(
        compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
    )

    val n = sorted.size
    val tails = IntArray(n)
    val prev = IntArray(n) { -1 }

    var size = 0

    for (i in 0 until n) {
        var left = 0
        var right = size

        while (left < right) {
            val mid = (left + right) / 2
            if (sorted[tails[mid]].second < sorted[i].second) {
                left = mid + 1
            } else {
                right = mid
            }
        }

        if (left > 0) {
            prev[i] = tails[left - 1]
        }

        tails[left] = i

        if (left == size) {
            size++
        }
    }

    val result = ArrayList<Pair<Int, Int>>()
    var index = tails[size - 1]

    while (index != -1) {
        result.add(sorted[index])
        index = prev[index]
    }

    return result.reversed()
}