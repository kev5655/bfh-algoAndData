package hash

import java.lang.Math.floorMod
import kotlin.math.ceil
import kotlin.math.pow

private fun hashFn(x: Int): Int {
    val y = 3*x + 3
    return floorMod(y,12) // !!! UPDATE HASH FUNCTION
}

private fun sondierungsFn(j: Int, k: Int): Int {
    // (⌈j/2⌉)^2
    val x = (ceil(j / 2.0)).pow(2.0).toInt()
    // (−1)^j
    val y = (-1.0).pow(j).toInt()
    return hashFn(k) + x * y // !!! UPDATE HASH FUNCTION!
}

fun main() {
    val m = 12 // !!! UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(12, 56, 47, 89, 31, 82, 10, 19, 36) // !!! UPDATE KEY'S
        .map { calcHash(it, ::hashFn) { _: Int -> 0 } }
    printHashTable(list)

    quadratischemSondierungsHashing(list, m, ::sondierungsFn).forEach { print(" $it, ") }
}

fun quadratischemSondierungsHashing(list: List<Data>, tableSize: Int, sondierungFn: (Int, Int) -> Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        println("Insert: ${data.value} | " + hashArray.joinToString { x -> "${x}, " })
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
