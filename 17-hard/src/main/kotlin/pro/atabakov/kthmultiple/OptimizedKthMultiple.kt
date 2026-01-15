package pro.atabakov.pro.atabakov.kthmultiple

import java.util.LinkedList
import java.util.Queue

fun getKthMagicNumber(k: Int): Int {
    var value = 0

    val queue3: Queue<Int> = LinkedList()
    val queue5: Queue<Int> = LinkedList()
    val queue7: Queue<Int> = LinkedList()

    queue3.add(1)

    repeat(k) {
        val v3 = queue3.peek()
        val v5 = if (queue5.isNotEmpty()) queue5.peek() else Int.MAX_VALUE
        val v7 = if (queue7.isNotEmpty()) queue7.peek() else Int.MAX_VALUE

        value = minOf(v3, v5, v7)

        when (value) {
            v3 -> {
                queue3.poll()
                queue3.add(3 * value)
                queue5.add(5 * value)
            }

            v5 -> {
                queue5.poll()
                queue5.add(5 * value)
            }

            v7 -> {
                queue7.poll()
            }
        }

        queue7.add(7 * value)
    }

    return value
}


fun main() {
    println(getKthMagicNumber(k = 5)) // there is only kth number, no array saved
}