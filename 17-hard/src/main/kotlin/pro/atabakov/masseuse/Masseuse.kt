package pro.atabakov.pro.atabakov.masseuse

import kotlin.math.max

fun maxMinutes(massages: IntArray): Int {
    var oneAway = 0
    var twoAway = 0

    for (i in massages.indices.reversed()) {
        val bestWith = massages[i] + twoAway
        val bestWithout = oneAway

        val current = max(bestWith, bestWithout)
        twoAway = oneAway
        oneAway = current
    }
    return oneAway
}

fun main() {
    val input = intArrayOf(30, 15, 60, 75, 45, 15, 15, 45)
    val minutes = maxMinutes(input)

    println("$minutes minutes")
}
