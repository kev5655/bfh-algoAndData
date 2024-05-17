package hash

import java.lang.Math.floorMod


private fun hashFn(x: Int): Int {
    return floorMod(x, 13) // !!!UPDATE HASH FUNCTION
}

private fun dynamicHashFn(index: Int, value: Int): Int {
//    return index - (1 + value % 11) % 13 // !!!UPDATE HASH FUNCTION
    val x = hashFn(index - floorMod(1 + value, 11))
    return x
}


fun main() {
    val m = 13 // !!!UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(14, 21, 27, 28, 8, 18, 15, 36, 5, 2) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn) { _: Int -> 0 } }
    printHashTable(list)

    orderedHashing(list, m)

}

fun orderedHashing(list: List<Data>, tableSize: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        if(hashArray[data.h1] == null) {
            hashArray[data.h1] = data.value
        } else {

        }
    }

}