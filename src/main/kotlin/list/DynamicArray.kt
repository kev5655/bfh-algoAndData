package list

class DynamicArray<T>(var array: Array<T>): List<T> {

    inline fun <reified T> create(size: Int): Array<T?> {
        return arrayOfNulls<T>(size)
    }

    override fun get(index: Int): T {
        checkIndex(index)
        return array[index] ?: throw NullPointerException("Element at index $index is null")
    }

    override fun add(index: Int, element: T) {
        checkIndex(index)
        val newArray = if(isIndexSmallerThanArray(index)) {
            array.copyOf(index + 1) as Array<T>
        } else {
            array.copyOf(array.size + 1) as Array<T>
        }

        for (i in array.size - 1 downTo index) {
            array[i + 1] = array[i]
        }
        // You can also use a SysCopy -> Shift elements to make space for the new element
        // System.arraycopy(array, index, array, index + 1, size - index)

        newArray[index] = element
        array = newArray;
    }


    override fun remove(index: Int) {
        for (i in index until array.size - 1) {
            array[i] = array[i + 1]
        }
        array = array.copyOf(array.size - 1) as Array<T>
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || isIndexSmallerThanArray(index)) {
            throw IndexOutOfBoundsException("Index: $index, Size: $array.size")
        }
    }

    private fun isIndexSmallerThanArray(index: Int) = index > array.size
}