package pro.atabakov.pro.atabakov.kthmultiple

import kotlin.math.pow

fun dumbKthMultiple(k: Int): List<Int> {
    val possibilities = allPossibleKFactors(k)
    possibilities.sort()
    return possibilities.take(k)
}

fun allPossibleKFactors(k: Int): MutableList<Int> {
    val values = mutableListOf<Int>()

    for (a in 0..k) { // loop for 3
        val powA = 3.0.pow(a.toDouble()).toInt()

        for (b in 0..k) { // loop for 5
            val powB = 5.0.pow(b.toDouble()).toInt()

            for (c in 0..k) { // loop for 7
                val powC = 7.0.pow(c.toDouble()).toInt()

                var value = powA * powB * powC

                // Overflow check
                if (value < 0 ||
                    powA == Int.MAX_VALUE ||
                    powB == Int.MAX_VALUE ||
                    powC == Int.MAX_VALUE
                ) {
                    value = Int.MAX_VALUE
                }

                values.add(value)
            }
        }
    }
    return values
}


fun main(){
    println(dumbKthMultiple(5).joinToString(" "))
}