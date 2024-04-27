package sorting.helper

fun swap(array: IntArray, i1: Int, i2: Int) {
    array[i1] = array[i2].also { array[i2] = array[i1] }
}