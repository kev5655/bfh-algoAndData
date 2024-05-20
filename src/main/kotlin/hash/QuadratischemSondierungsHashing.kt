package hash

import java.lang.Math.floorMod
import kotlin.math.ceil
import kotlin.math.pow

private fun hashFn(x: Int): Int {
    return floorMod(x, 13) // !!! UPDATE HASH FUNCTION
}

private fun sondierungsFn(j: Int, k: Int): Int {
    // (⌈j/2⌉)^2
    val x = (ceil(j / 2.0)).pow(2.0).toInt()
    // (−1)^j
    val y = (-1.0).pow(j).toInt()
    return k + x * y // !!! UPDATE HASH FUNCTION
}

fun main() {
    val m = 13 // !!! UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(5, 1, 19, 23, 14, 17, 32, 30, 2) // !!! UPDATE KEY'S
        .map { calcHash(it, ::hashFn) { _: Int -> 0 } }
    printHashTable(list)

    quadratischemSondierungsHashing(list, m, ::sondierungsFn).forEach { print(" $it, ") }
}

fun quadratischemSondierungsHashing(list: List<Data>, tableSize: Int, sondierungFn: (Int, Int) -> Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        var currentIndex = data.h1
        val currentValue = data.value
        var counter = 1
        while (true) {
            if (hashArray[currentIndex] == null) {
                hashArray[currentIndex] = currentValue
                break
            }

            currentIndex = (sondierungFn(counter, data.value) + tableSize) % tableSize
            counter++
        }
    }
    return hashArray
}
