package pro.atabakov.pro.atabakov.majority

fun findMajorityElement(array: IntArray): Int {
    val candidate = getCandidate(array)
    return if (validate(array, candidate)) candidate else -1
}

fun getCandidate(array: IntArray): Int {
    var majority = 0
    var count = 0

    for (n in array) {
        if (count == 0) { // no dominant element
            majority = n
        }

        if (n == majority) {
            count++
        } else {
            count--
        }
    }
    return majority
}

fun validate(array: IntArray, majority: Int): Boolean {
    var count = 0

    for (n in array) {
        if (n == majority) {
            count++
        }
    }

    return count > array.size / 2
}

fun main(){
    val m = findMajorityElement(intArrayOf(1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7))
//    val m = findMajorityElement(intArrayOf(1,2,5,9,5,9,5,5,5))
//    val m = findMajorityElement(intArrayOf(3,1,7,1,3,7,3,7,1,7,7))
    println(m)
}