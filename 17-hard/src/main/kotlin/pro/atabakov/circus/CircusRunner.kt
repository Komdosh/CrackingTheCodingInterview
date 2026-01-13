package pro.atabakov.pro.atabakov.circus

fun main(){
    val people = listOf(
        65 to 100,
        70 to 150,
        56 to 90,
        75 to 190,
        60 to 95,
        62 to 93,
        63 to 94,
        68 to 110
    )

    val towerTails = longestTowerTails(people)

    towerTails.forEach { (height, weight) ->
        println("(${height}, ${weight})")
    }
    println("=========")

    val towerNaive = longestTower(people)

    towerNaive.forEach { (height, weight) ->
        println("(${height}, ${weight})")
    }
    println("=========")

    val htWts = people.mapTo(ArrayList()) { HtWt(it.first, it.second) }

    val towerDp = longestIncreasingSeqDP(htWts)

    towerDp.forEach(::println)
    println("=========")

    val towerRecursion = longestIncreasingSeqRecursion(htWts)

    towerRecursion.forEach(::println)

}