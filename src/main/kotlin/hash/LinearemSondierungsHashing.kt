package hash

import java.lang.Math.floorMod

private fun hashFn(x: Int): Int {
    val y = 3*x + 3
  return floorMod(y,12)
//return floorMod(x, 13) // !!!UPDATE HASH FUNCTION
}

fun main() {

    val m = 12 // !!!UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(12, 56, 47, 89, 31, 82, 10, 19, 36) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn) { _: Int -> 0 } }
    printHashTable(list)

    linearemSondierungsHashing(list, m).forEach { print(" $it, ") }
}

fun linearemSondierungsHashing(list: List<Data>, tableSize: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        println("Insert: ${data.value} | " + hashArray.joinToString { x -> "${x}, " })
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
