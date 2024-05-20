package serach

import java.util.Random

fun main() {
    val num = 100000
    val sortedList = List(num){Random().nextInt(num)}.toSet().sorted()
    println(binarySearch(sortedList, 555))
    println(binarySearch(sortedList, 666))
    println(binarySearch(sortedList, 777))

}

private tailrec fun binarySearch(list: List<Int>, search: Int): Int? {
    if(list.isEmpty()) return null
    val half = list[list.size / 2]

    return when {
        half == search -> list[list.size / 2]
        half > search -> binarySearch(list.subList(0, list.size / 2), search)
        half < search -> binarySearch(list.subList(list.size / 2 + 1, list.size), search)
        else -> return null
    }
}