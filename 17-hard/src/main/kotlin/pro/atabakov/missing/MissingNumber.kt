package pro.atabakov.pro.atabakov.missing

class BitInteger(val value: Int) {
    private val bitArray: IntArray = IntArray(32) { i ->
        (value shr i) % 2
    }

    fun fetch(column: Int): Int = bitArray[column]
}

fun missingNumber(array: List<BitInteger>, index: Int): Int {
    if (index > 31) {
        return 0
    }

    val listCapacity = array.size / 2
    val zeroes = ArrayList<BitInteger>(listCapacity)
    val ones = ArrayList<BitInteger>(listCapacity)

    for (currentValue in array) {
        if (currentValue.fetch(index) == 1) {
            ones.add(currentValue)
        } else {
            zeroes.add(currentValue)
        }
    }

    val missedBit = if (zeroes.size <= ones.size) 0 else 1
    val nextCandidate = if (missedBit == 0) zeroes else ones
    val value = missingNumber(nextCandidate, index + 1)

    return value or (missedBit shl index)
}

fun main() {
    val array = listOf(
        BitInteger(0),
        BitInteger(1),
        BitInteger(2),
        BitInteger(3),
        BitInteger(4),
        BitInteger(5),
        BitInteger(6),
        BitInteger(7),
        BitInteger(8),
        BitInteger(9),
        BitInteger(10),
    )

    println(missingNumber(array, 0))
}