package sorting

import sorting.helper.*
import kotlin.system.exitProcess

fun hashSort(array: IntArray): IntArray {
    val tree = createTreeBySiftUp(array, null)

    printTreeNormal(tree)

    val sortetList = array.map { _ ->
        println("Before Tree with Root ${tree?.element}");
        printTree(tree)
        tree?.let { heapify(it) }
        println("After Tree with Root ${tree?.element}");
        printTree(tree)

        val result = tree!!.element
//        val (_, lastElement) = getLastNode(tree)
//        swapNode(tree, lastElement)
//        getLastNode(tree).second.destroy()
        println("After Destroy Tree with Root ${tree?.element}");
        printTree(tree)
        result
    }

    println(sortetList)


    return intArrayOf()
}

fun heapify(tree: TreeNode<Int>, level: Int = findMaxDepth(tree) - 1) {
    val lastP = getNodeByLevel(tree, level)
    lastP.forEach {
        it.swapGreater(it.right)
        it.swapGreater(it.left)
    }

    if (level != 0) {
        heapify(tree, level - 1)
    }

    return
}

fun main() {
    val array = intArrayOf(10, 3, 26, 9, 5, 31)
    val sortedIntArray = hashSort(array)
    sortedIntArray.forEach { print("$it ") }
    println()
}

