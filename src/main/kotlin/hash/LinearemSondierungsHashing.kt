package hash

import java.lang.Math.floorMod

private fun hashFn(x: Int): Int {
    return floorMod(x, 13) // !!!UPDATE HASH FUNCTION
}

fun main() {

    val m = 13 // !!!UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(5, 1, 19, 23, 14, 17, 32, 30, 2) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn) { _: Int -> 0 } }
    printHashTable(list)

    linearemSondierungsHashing(list, m).forEach { print(" $it, ") }
}

fun linearemSondierungsHashing(list: List<Data>, tableSize: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        var currentIndex = data.h1
        val currentValue = data.value
        while (true) {
            if(hashArray[currentIndex] == null) {
                hashArray[currentIndex] = currentValue
                break
            }

            currentIndex = (currentIndex - 1 + tableSize) % tableSize
        }
    }
    return hashArray
}
