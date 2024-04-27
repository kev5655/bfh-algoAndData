package serach

class TreeNode<T>(
    var value: T?,
    var parent: TreeNode<T>? = null,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null
) {

    fun clear() {
        value = null
        parent = null
        left = null
        right = null
    }
}

class BinaryTree<T> {

    val root: TreeNode<T>? = null

    fun createTree(list: List<T>) {
        val sorted = list //.sorted()
    }
}

fun <T>nodeOf(x: T): TreeNode<T> {
    return TreeNode(x)
}