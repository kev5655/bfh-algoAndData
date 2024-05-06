package sorting

import sorting.helper.*

fun hashSort(array: IntArray): IntArray {
    val tree = createTreeBySiftUp(array, null)

//    printTreeNormal(tree)

    val sortedList = array.map { _ ->
//        println("Before Tree with Root ${tree?.element}");
//        printTree(tree)
        if(tree?.left == null) return@map tree!!.element
        heapify(tree)
//        println("After Tree with Root ${tree.element}");
//        printTree(tree)

        val result = tree.element
        val lastNode = getLastNodeFromBalancedTree(tree)
        tree.swapWith(lastNode)
        getLastNodeFromBalancedTree(tree).destroy()
//        println("After Destroy Tree with Root ${tree?.element}");
//        printTree(tree)
        result
    }

    println("Sorted List $sortedList")


    return intArrayOf()
}

fun heapify(tree: TreeNode<Int>, level: Int = findMaxDepth(tree) - 1) {
    val lastP = getNodeByLevel(tree, level)
    lastP.forEach {
        it.swapGreater(it.right)
        it.swapGreater(it.left)
    }

    if (level != 0) heapify(tree, level - 1)
    return
}

fun main() {
    val array = intArrayOf(10, 3, 26, 9, 5, 31)
    val sortedIntArray = hashSort(array)
    sortedIntArray.forEach { print("$it ") }
    println()
}

