package serach

class TreeNode<T>(
    private var value: T?,
    private var parent: TreeNode<T>? = null,
    private var left: TreeNode<T>? = null,
    private var right: TreeNode<T>? = null
) {
    fun clear() {
        value = null
        parent = null
        left = null
        right = null
    }
}

fun <T>nodeOf(x: T): TreeNode<T> {
    return TreeNode(x)
}