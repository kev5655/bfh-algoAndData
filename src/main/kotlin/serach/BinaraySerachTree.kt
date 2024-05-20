package serach

import sorting.helper.TreeNode
import sorting.helper.createTreeBySiftUp

fun main() {
    val num = 20
    val sortedList = List(num){it}.toSet().sorted()
    val tree = createTreeBySiftUp(sortedList.toIntArray(), null)
    println(binarySearch(tree, 15))
    println(binarySearch(tree, 666))
    println(binarySearch(tree, 777))

}

private tailrec fun binarySearch(tree: TreeNode<Int>?, search: Int): Int? {
    if(tree == null) return null

    return when {
        tree.element == search -> tree.element
        tree.element > search -> binarySearch(tree.left, search)
        tree.element < search -> binarySearch(tree.right, search)
        else -> return null
    }
}