package sorting


import sorting.helper.TreeNode
import sorting.helper.nodeOf
import sorting.helper.printTree


fun hashSort(array: IntArray): IntArray {
    val tree = createTree(array)
    printTree(tree[0])

    return intArrayOf()
}

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
    println("left: $left")
    println("right $right")
    return Pair(left.toIntArray(), right.toIntArray())
}

fun alternatingBooleanListByPow(size: Int): BooleanArray {
    var changer = false
    var counter = 0
    val doubleSumPowList = doubleSumPowList()
    println(doubleSumPowList.toList())
    val list = BooleanArray(size) { index ->
        if(index == doubleSumPowList[counter]) {
            changer = !changer
            counter++
        }
        changer
    }
    println(list.toList())
//    oversee(list)
    return list
}

fun doubleSumPowList(): IntArray {
    val doublePowList = powList(20).zip(powList(20)).flatMap { (a, b) -> listOf(a, b) }
    return doublePowList.scan(0) { sum, element -> sum + element }.toIntArray()
}


fun main() {
    val array = intArrayOf(10,3,26,9,5,31)
    val sortedIntArray = hashSort(array)
    sortedIntArray.forEach { print("$it ") }
    println()
}


private fun oversee(list: BooleanArray) {
    val foldObj = object {
        val map = mutableMapOf<Int, Int>()
        var lastIndex: Int? = null
        var lastValue: Boolean? = null
    }
    val observer = list.toList().foldIndexed(foldObj) { i, store, item ->
        if (store.lastValue == null) {
            store.map[i] = 1
            store.lastIndex = i
            store.lastValue = item
            return@foldIndexed store
        } else {
            if (store.lastValue != item) {
                store.map[i] = 1
                store.lastIndex = i
                store.lastValue = item
            } else {
                store.map[store.lastIndex!!] = 1 + store.map[store.lastIndex]!!
            }
        }
        store
    }
    println(observer.map)
}

fun leftList(): IntArray {
    val stepList = powList(10)
    val powSummed = powSummedList(stepList).toIntArray()
    val leftList = appliedList(0, 100, stepList, powSummed)
    println(leftList.toList())
    return leftList
}
fun rightList(): IntArray {
    val stepList = powList(10).drop(1).toIntArray()
    val powSummed = powSummedList(powList(10)).toIntArray()
    val rightList = appliedList(1, 100, stepList, powSummed)
    println(rightList.toList())
    return rightList
}
fun powList(size: Int) = IntArray(size) { 1 shl it }
fun powSummedList(x: IntArray) = x.scan(0) { sum, element -> sum + element }.drop(1)
fun appliedList(start: Int, size: Int, stepperList: IntArray, stepConditionList: IntArray): IntArray {
    var offset = start
    var stepper = 0
    return IntArray(size) { index ->
        if (index == stepConditionList[stepper]) {
            offset += stepperList[stepper]
            stepper++
        }
        index + offset
    }
}
