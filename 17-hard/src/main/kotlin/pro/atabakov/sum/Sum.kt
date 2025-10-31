package pro.atabakov.pro.atabakov.sum

fun sum32(a: Int, b: Int): Int {
    var carryBit = false

    var bitMaskPosition = 1
    var result = 0
    while (bitMaskPosition != 0) {
        val aBit = (a and bitMaskPosition) != 0
        val bBit = (b and bitMaskPosition) != 0

        when {
            !aBit && !bBit -> {
                if (carryBit) {
                    result = result or bitMaskPosition
                    carryBit = false
                }
            }

            (aBit && !bBit) || (!aBit && bBit) -> {
                if (carryBit) {
                    // do nothing
                } else {
                    result = result or bitMaskPosition
                }
            }

            aBit && bBit /*else*/ -> {
                if (carryBit) {
                    result = result or bitMaskPosition
                } else {
                    carryBit = true
                }
            }
        }

        bitMaskPosition = bitMaskPosition shl 1
    }

    return result
}


fun optimizedSum32(a: Int, b: Int): Int {
    var a = a
    var b = b
    while (b != 0) {
        val sum = a xor b // суммирование без переноса
        val carry = (a and b) shl 1 // перенос без суммирования
        a = sum
        b = carry
    }
    return a
}

fun main() {
    val answer = sum32(8, 12)
    val optimizedAnswer = optimizedSum32(8, 12)

    println("Answer: $answer")
    println("Optimized Answer: $optimizedAnswer")
}