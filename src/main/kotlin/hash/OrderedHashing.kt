package hash

import java.lang.Math.floorMod


private fun hashFn1(x: Int): Int {
    return floorMod(x, 13) // !!!UPDATE HASH FUNCTION
}

private fun hashFn2(x: Int): Int {
//    return index - (1 + value % 11) % 13 // !!!UPDATE HASH FUNCTION
    return 1 + floorMod(x, 11)
}


fun main() {
    val m = 13 // !!!UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(14, 21, 27, 28, 8, 18, 15, 36, 5, 2) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn1, ::hashFn2) }
    printHashTable(list)

    orderedHashing(list, m).forEach { print(" $it, ") }

}

fun orderedHashing(list: List<Data>, tableSize: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
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
