package sorting



fun insertionSortImperative(array: IntArray): IntArray {
    for (i in 1 until array.size) {
        val key: Int = array[i]
        var j = i - 1
        /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
        while (j >= 0 && array[j] > key) {
            array[j + 1] = array[j]
            j--
        }
        array[j + 1] = key
    }
    return array
}

fun insertionSortDeclarative(array: IntArray): IntArray {
    array.indices.drop(1).forEach { i ->
        val key = array[i]
        var j = i - 1
        while (j >= 0 && array[j] > key) {
            array[j + 1] = array[j]
            j--
        }
        array[j + 1] = key
    }
    return array
}


fun main(){

    val array = intArrayOf(3, 7, 9, 5, 8, 2, 1, 6, 4)
    val sortedIntArray = insertionSortImperative(array)
    sortedIntArray.forEach { print("$it ") }
    println()

    val array2 = intArrayOf(3, 7, 9, 5, 8, 2, 1, 6, 4)
    val sortedIntArray2 = insertionSortDeclarative(array2)
    sortedIntArray2.forEach { print("$it ") }
    println()
}

