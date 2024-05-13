package hash


private fun hashFn(x: Int): Int {
    return x % 13 // !!!UPDATE HASH FUNCTION
}

private fun dynamicHashFn(index: Int, value: Int): Int {
//    return index - (1 + value % 11) % 13 // !!!UPDATE HASH FUNCTION
    return hashFn(index - (1 + value % 11))
}


fun main() {

    val list = listOf(14, 21, 27, 28, 8, 18, 15, 36, 5, 2) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn) { _: Int -> 0 } }
    printHashTable(list)

}

fun brentsDoubleHashing(list: List<Data>, tableSize: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        if (hashArray[data.h1] == null) {
            hashArray[data.h1] = data.value
        } else {
            var index = data.h1
            val oldValue = hashArray[data.h1]!!
            val newH1 = dynamicHashFn(index, data.value)
            val oldH2 = dynamicHashFn(index, oldValue)

            while (true) {
                if (hashArray[newH1] == null) {
                    hashArray[newH1] = data.value
                    break;
                }
                if (hashArray[oldH2] == null) {
                    hashArray[oldH2] == data.value
                    break;
                }


            }
        }
    }


    return arrayOf()
}