package sorting.helper

// Creating a try by the SiftUp order form a list see https://en.wikipedia.org/wiki/Heapsort
fun createTreeBySiftUp(array: IntArray, parent: TreeNode<Int>?): TreeNode<Int>? {
    if (array.isEmpty()) return null
    if (array.size == 1) return TreeNode(array[0], parent)
    val first = array[0]
    val newArray = array.copyOfRange(1, array.size)
    val (left, right) = split2Pow(newArray)
    val root = TreeNode(first, parent)
    root.right = createTreeBySiftUp(right, root)
    root.left = createTreeBySiftUp(left, root)
    return root
}

private fun split2Pow(array: IntArray): Pair<IntArray, IntArray> {
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()
    alternatingBooleanListByPow(array.size).forEachIndexed { i, item ->
        if(item) left.add(array[i])
        else right.add(array[i])
    }
//    println("left: $left")
//    println("right $right")
    return Pair(left.toIntArray(), right.toIntArray())
}

private fun alternatingBooleanListByPow(size: Int): BooleanArray {
    var changer = false
    var counter = 0
    val doubleSumPowList = doubleSumPowList()
//    println(doubleSumPowList.toList())
    val list = BooleanArray(size) { index ->
        if(index == doubleSumPowList[counter]) {
            changer = !changer
            counter++
        }
        changer
    }
//    println(list.toList())
    return list
}

private fun doubleSumPowList(): IntArray {
    // Todo Fix the 20
    val doublePowList = powList(20).zip(powList(20)).flatMap { (a, b) -> listOf(a, b) }
    return doublePowList.scan(0) { sum, element -> sum + element }.toIntArray()
}
private fun powList(size: Int) = IntArray(size) { 1 shl it }