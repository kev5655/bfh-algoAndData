package sorting

fun main() {
    val array = intArrayOf(3, 2, 5, 0, 1, 8, 7, 6, 9, 4)
    val sortedIntArray = quickSort(array)
    sortedIntArray.forEach { print("$it ") }
}

fun quickSort(array: IntArray): IntArray {
    if (array.size <= 1) return array

    val (pivot, partitionLeft, partitionRight) = partition2(array)

    val left = quickSort(partitionLeft)
    val right = quickSort(partitionRight)
    return left + pivot + right
}

private fun partition1(array: IntArray): Triple<Int, IntArray, IntArray> {
    var pivotIndex = array.size - 1
    var indexUp = array.size - 2
    var indexDown = 0

    while (indexUp > indexDown) {
        while (array[indexUp] > array[pivotIndex] && indexUp > indexDown) {
            indexUp--
        }
        while (array[indexDown] <= array[pivotIndex] && indexDown < pivotIndex) {
            indexDown++
        }
        if (indexUp > indexDown) {
            swap(array, indexUp, indexDown)
        }
    }
    if (array[pivotIndex] < array[indexDown]) {
        swap(array, indexDown, pivotIndex)
        pivotIndex = indexDown
    }

    val partitionLeft = array.slice(0 until pivotIndex).toIntArray()
    val partitionRight = array.slice(pivotIndex + 1 until array.size).toIntArray()
    return Triple(array[pivotIndex], partitionLeft, partitionRight)
}

private fun partition2(array: IntArray): Triple<Int, IntArray, IntArray> {

    val left = array.filter { it < array.last() }.toIntArray()
    val right = array.filter { it > array.last() }.toIntArray()

    return Triple(array.last(), left, right)
}