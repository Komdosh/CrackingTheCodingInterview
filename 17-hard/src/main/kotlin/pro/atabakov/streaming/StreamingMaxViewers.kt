package pro.atabakov.pro.atabakov

import java.util.PriorityQueue


data class TsView(val time: Int, val isStart: Boolean) : Comparable<TsView> {
    override fun compareTo(other: TsView): Int {
        val timeDiff = time.compareTo(other.time)
        if (timeDiff != 0) return timeDiff

        return isStart.compareTo(other.isStart)
    }
}


fun max(viewsTs: Array<Pair<Int, Int>>): Int {
    if (viewsTs.isEmpty()) return 0

    val viewEventTS = PriorityQueue<TsView>()

    for ((start, end) in viewsTs) {
        viewEventTS.add(TsView(start, true))
        viewEventTS.add(TsView(end, false))
    }

    var max = 1
    var current = 0
    for (view in viewEventTS) {
        current += if (view.isStart) 1 else -1

        if (current > max) {
            max = current
        }
    }

    return max
}

fun main() {
    println(max(arrayOf(Pair(9, 12), Pair(9, 14), Pair(10, 12), Pair(1, 10), Pair(1, 10), Pair(2, 4), Pair(2, 3))))
}