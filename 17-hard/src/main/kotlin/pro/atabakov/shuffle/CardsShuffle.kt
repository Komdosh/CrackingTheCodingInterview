package pro.atabakov.pro.atabakov.shuffle

import kotlin.random.Random

val randomGenerator = Random(0)
fun shuffleCards(cards: MutableList<Int>): List<Int> {
    val shuffledCards: MutableList<Int> = mutableListOf()
    while (cards.isNotEmpty()) {
        val index = randomGenerator.nextInt(cards.size)
        shuffledCards.add(cards.removeAt(index))
    }

    return shuffledCards
}

fun shuffleCardsInplace(cards: MutableList<Int>): List<Int> {
    for (index in cards.indices) {
        val randomIndex = randomGenerator.nextInt(cards.size)
        val temp = cards[randomIndex]
        cards[randomIndex] = cards[index]
        cards[index] = temp
    }

    return cards
}

fun main() {
    println(shuffleCards((0..<52).toMutableList()))
    println(shuffleCardsInplace((0..<52).toMutableList()))
}