package sorting


fun bubbleSortImperative(array: IntArray): IntArray {
    var hasSwapped = false
    for (i in 0 until array.size - 1) {
        for (j in 0 until  array.size - 1) {
            if(array[j] > array[j + 1]) {
                hasSwapped = true
                swap(array, j, j + 1)
            }
        }
        if(!hasSwapped) break
    }

    return array
}

fun swap(array: IntArray, i1: Int, i2: Int) {
    array[i1] = array[i2].also { array[i2] = array[i1] }
}


fun main(){

    val array = intArrayOf(3, 7, 9, 5, 8, 2, 1, 6, 4)
    val sortedIntArray = bubbleSortImperative(array)
    sortedIntArray.forEach { print("$it ") }
    println()
}

