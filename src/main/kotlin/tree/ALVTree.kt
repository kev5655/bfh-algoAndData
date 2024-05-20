package tree

fun main() {

}

data class ALVTreeNode<T>(
    var element: T,
    var parent: ALVTreeNode<T>? = null,
    var left: ALVTreeNode<T>? = null,
    var right: ALVTreeNode<T>? = null,
) {
    override fun toString(): String {
        return "TreeNode(element=$element, parent=$parent)"
    }
}
