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

class BinaryTree {

}