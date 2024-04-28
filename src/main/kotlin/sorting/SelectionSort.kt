package sorting


fun selectionSortImperative(array: IntArray): IntArray {
    val size = array.size

    for (i in 0 until size) {
        var minIndex = i
        for (j in i until size) {
            if (array[minIndex] > array[j]) {
                minIndex = j
            }
        }
        if (i != minIndex) swap(array, i, minIndex)
    }
    return array
}

fun selectionSortDeclarative(array: IntArray): IntArray {
    array.indices.forEach { i ->
        val minIndex = (i until array.size).minByOrNull { array[it] }
        minIndex?.let { swap(array, i, it) }
    }
    return array
}

fun main() {
    val intArray = intArrayOf(3, 7, 9, 5, 8, 2, 1, 6, 4)
    val sortedIntArray = selectionSortImperative(intArray)
    sortedIntArray.forEach { print("$it ") }
    println()

    val intArray2 = intArrayOf(3, 7, 9, 5, 8, 2, 1, 6, 4)
    val sortedIntArray2 = selectionSortDeclarative(intArray2)
    sortedIntArray2.forEach { print("$it ") }
    println()
}