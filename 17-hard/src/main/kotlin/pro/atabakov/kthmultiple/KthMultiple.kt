package pro.atabakov.pro.atabakov.kthmultiple

import kotlin.math.min

fun kthMultiple(k: Int): Array<Int> {
    val dp = Array(k) { 1 }

    var i3 = 0
    var i5 = 0
    var i7 = 0

    var next3 = 3
    var next5 = 5
    var next7 = 7

    for(i in 1..<k){
        val min = min(min(next3, next5), next7)

        dp[i] = min

        if (min == next3) next3 = dp[++i3] * 3
        if (min == next5) next5 = dp[++i5] * 5
        if (min == next7) next7 = dp[++i7] * 7
    }

    return dp
}

fun main(){
    println(kthMultiple(7).joinToString(" "))
}