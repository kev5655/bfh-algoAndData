package hash

import java.lang.Math.floorMod


private fun hashFn1(x: Int): Int {
//    val y = floorMod(x, 13) // !!!UPDATE HASH FUNCTION
    val y = floorMod(x, 11)
    return y
}

private fun hashFn2(x: Int): Int {
//    return index - (1 + value % 11) % 13 // !!!UPDATE HASH FUNCTION
//    val y = 1 + floorMod(x, 11)
    val y = 1 + floorMod(x, 9)
    return y
}


fun main() {
    val m = 11 // !!!UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(65, 33, 5, 19, 22, 15, 27, 12, 28, 1) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn1, ::hashFn2) }
    printHashTable(list)

    orderedHashing(list, m).forEach { print(" $it, ") }

}

fun orderedHashing(list: List<Data>, tableSize: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        println("Insert: ${data.value} | " + hashArray.joinToString { x -> "${x}, " })
        var currentIndex = data.h1
        var currentValue = data.value

        while (true) {
            val existingValue = hashArray[currentIndex]

            if (existingValue == null) {
                hashArray[currentIndex] = currentValue
                break
            }

            val (smallerValue, largerValue) = if (existingValue < currentValue) {
                Pair(existingValue, currentValue)
            } else {
                Pair(currentValue, existingValue)
            }

            hashArray[currentIndex] = smallerValue

            val shiftValue = list.first { it.value == largerValue }.h2
            currentIndex = (currentIndex - shiftValue + tableSize) % tableSize

            currentValue = largerValue
        }
    }
    return hashArray
}
