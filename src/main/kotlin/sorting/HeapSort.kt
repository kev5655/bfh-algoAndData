package sorting

import sorting.helper.TreeNode
import sorting.helper.createTree
import sorting.helper.getFistLastNode
import sorting.helper.printTree


fun hashSort(array: IntArray): IntArray {
    val tree = createTree(array)[0]
    printTree(tree)

    heapify(tree)

    return intArrayOf()
}

fun heapify(tree: TreeNode<Int>) {
    val lowNode = getFistLastNode(tree)


    if (lowNode.element!! > lowNode.parent!!.element!!) {
        changeNode(lowNode, lowNode.parent)
    }



}



fun main() {
    val array = intArrayOf(10,3,26,9,5,31)
    val sortedIntArray = hashSort(array)
    sortedIntArray.forEach { print("$it ") }
    println()
}

