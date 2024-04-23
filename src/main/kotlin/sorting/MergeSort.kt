package sorting


fun mergeSort(array: IntArray): IntArray {
    if (array.size <= 1) return array // Direct return for base case

    val middleIndex = array.size / 2
    val s1 = mergeSort(array.copyOfRange(0, middleIndex))
    val s2 = mergeSort(array.copyOfRange(middleIndex, array.size))

    val intArray = ArrayList<Int>(s1.size + s2.size)
    var s1Index = 0
    var s2Index = 0

    // Merge the sorted halves
    while (s1Index < s1.size && s2Index < s2.size) {
        if (s1[s1Index] < s2[s2Index]) {
            intArray.add(s1[s1Index++])
        } else {
            intArray.add(s2[s2Index++])
        }
    }

    // Add all remaining elements from s1 or s2
    intArray.addAll(s1.slice(s1Index until s1.size))
    intArray.addAll(s2.slice(s2Index until s2.size))

    return intArray.toIntArray()
}



fun main() {
    val array = intArrayOf(3, 7, 9, 5, 8, 2, 1, 6, 4)
    val sortedIntArray = mergeSort(array)
    sortedIntArray.forEach { print("$it ") }
    println()
}