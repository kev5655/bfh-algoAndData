package sorting

import sorting.helper.*


fun hashSort(array: IntArray): IntArray {
    val tree = createTreeBySiftUp(array, null)
    printTree(tree)

    tree?.let { heapify(it) }

    return intArrayOf()
}

fun heapify(tree: TreeNode<Int>) {
   val lastP = getLastParents(tree)
    lastP.forEach { println("Tree with Root ${it.element}"); printTree(it) }


}



fun main() {
    val array = intArrayOf(10,3,26,9,5,31)
    val sortedIntArray = hashSort(array)
    sortedIntArray.forEach { print("$it ") }
    println()
}

