package hash

import java.lang.Math.floorMod

private fun hashFn(x: Int): Int {
    return floorMod(x, 13) // !!!UPDATE HASH FUNCTION
}

private fun sondierungsFn(j: Int, k: Int): Int {
    return 1 + j
}

fun main() {

    val m = 13 // !!!UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(5, 1, 19, 23, 14, 17, 32, 30, 2) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn) { _: Int -> 0 } }
    printHashTable(list)

    linearemSondierungsHashingWithFN(list, m, ::sondierungsFn).forEach { print(" $it, ") }
}

fun linearemSondierungsHashingWithFN(list: List<Data>, tableSize: Int, sondierungFn: (Int, Int) -> Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        var currentIndex = data.h1
        val currentValue = data.value
        while (true) {
            if(hashArray[currentIndex] == null) {
                hashArray[currentIndex] = currentValue
                break
            }

            currentIndex = (sondierungFn(currentIndex, 0) + tableSize) % tableSize
        }
    }
    return hashArray
}
