package hash

import java.lang.Math.floorMod


private fun hashFn(x: Int): Int {
    return floorMod(x, 11) // !!!UPDATE HASH FUNCTION
}

private fun dynamicHashFn(index: Int, value: Int): Int {
//    return index - (1 + value % 11) % 13 // !!!UPDATE HASH FUNCTION
//    val x = hashFn(index - floorMod(1 + value, 11))
    val x = hashFn(index + floorMod(value, 9))
    return x
}


fun main() {

    val m = 11 // !!!UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(65, 33, 5, 19, 22, 15, 27, 12, 28, 1) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn) { _: Int -> 0 } }
   printHashTable(list)

    brentsDoubleHashing(list, m).forEach { print(" $it, ") }

}

fun brentsDoubleHashing(list: List<Data>, tableSize: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        println("Insert: ${data.value} | " + hashArray.joinToString { x -> "${x}, " })
        if (hashArray[data.h1] == null) {
            hashArray[data.h1] = data.value
        } else {
            var currentIndex = data.h1
            var currentValue = hashArray[data.h1]!!

            while (true) {
                val nextIndex = dynamicHashFn(currentIndex, data.value)
                if (hashArray[nextIndex] == null) {
                    hashArray[nextIndex] = data.value
                    break;
                }
                val fallbackIndex = dynamicHashFn(currentIndex, currentValue)
                if (hashArray[fallbackIndex] == null) {
                    hashArray[fallbackIndex] = hashArray[currentIndex]
                    hashArray[currentIndex] = data.value
                    break;
                }
                currentIndex = nextIndex
                currentValue = hashArray[nextIndex]!!

            }
        }
    }


    return hashArray
}