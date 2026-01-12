package pro.atabakov.pro.atabakov.counttwos

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

fun main(){
    println(countOfTwos(25))
}