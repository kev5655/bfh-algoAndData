package sorting

import sorting.helper.createTreeBySiftUp
import sorting.helper.printTree

fun main() {
    val array = intArrayOf(10, 3, 26, 9, 5, 31)
    val tree = createTreeBySiftUp(array, null)

    printTree(tree)

    println(sorting.helper.getLastNode(tree!!))

    val array2 = intArrayOf(10, 3, 26, 9, 5)
    val tree2 = createTreeBySiftUp(array2, null)

    printTree(tree2)

    println(sorting.helper.getLastNode(tree2!!))

    val array3 = intArrayOf(10, 3, 26, 9, 5, 31, 32)
    val tree3 = createTreeBySiftUp(array3, null)

    printTree(tree3)

    println(sorting.helper.getLastNode(tree3!!))
}