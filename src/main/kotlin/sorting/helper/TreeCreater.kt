package sorting.helper

fun createTree(array: IntArray): MutableList<TreeNode<Int>> {
    if (array.size == 0) return mutableListOf()
    if (array.size == 1) return mutableListOf(nodeOf(array[0]))
    val first = array[0]
    val newArray = array.copyOfRange(1, array.size)
    val (left, right) = split2Pow(newArray)
    return mutableListOf(TreeNode(first, createTree(left) + createTree(right)))
}

fun split2Pow(array: IntArray): Pair<IntArray, IntArray> {
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

fun alternatingBooleanListByPow(size: Int): BooleanArray {
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

fun doubleSumPowList(): IntArray {
    val doublePowList = powList(20).zip(powList(20)).flatMap { (a, b) -> listOf(a, b) }
    return doublePowList.scan(0) { sum, element -> sum + element }.toIntArray()
}
fun powList(size: Int) = IntArray(size) { 1 shl it }