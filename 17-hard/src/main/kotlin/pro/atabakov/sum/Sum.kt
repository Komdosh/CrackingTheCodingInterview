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

fun main() {
    val answer = sum32(8, 12)
    println("Answer: $answer")
}