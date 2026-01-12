package pro.atabakov.pro.atabakov.counttwos

import kotlin.collections.plusAssign
import kotlin.math.pow
import kotlin.text.compareTo

fun countOfTwos(n: Int): Int {
    if (n < 2) return 0

    var count = 0
    var factor = 1

    while (n / factor != 0) {
        val lower = n % factor
        val current = (n / factor) % 10
        val higher = n / (factor * 10)

        count += when {
            current < 2 -> higher * factor
            current == 2 -> higher * factor + lower + 1
            else -> (higher + 1) * factor
        }

        factor *= 10
    }

    return count
}

fun countOfTwosFromBook(n: Int): Int {
    var count = 0
    val len = n.toString().length
    for (digit in 0..<len) {
        count += count2sInRangeAtDigit(n, digit);
    }

    return count
}

fun count2sInRangeAtDigit(number: Int, d: Int): Int {
    val powerOf10 = 10.0.pow(d)
    val nextPowerOf10 = powerOf10 * 10
    val right = number % powerOf10

    val roundDown = number - number % nextPowerOf10
    val roundUp = roundDown + nextPowerOf10
    val digit = (number / powerOf10) % 10

    val count = if (digit < 2) {
        roundDown / 10
    } else if (digit.toInt() == 2) {
        roundDown / 10 + right + 1
    } else {
        roundUp / 10
    }

    return count.toInt()
}

fun main() {
    println(countOfTwos(25))
    println(countOfTwosFromBook(25))
}