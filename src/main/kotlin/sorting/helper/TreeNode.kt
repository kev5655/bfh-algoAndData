package sorting.helper

class TreeNode<T>() {

    var element: T? = null
    var parent: TreeNode<T>? = null
    var child: MutableList<TreeNode<T>> = mutableListOf()

    constructor(element: T) : this() {
        this.element = element
    }

    constructor(element: T, child: List<TreeNode<T>>) : this() {
        this.element = element
        this.child = child.toMutableList();
    }

    fun destroy() {
        element = null
        parent = null
        child.clear()
    }

}


fun <T>nodeOf(x: T): TreeNode<T> {
    return TreeNode(x)
}

fun <T> printTree(node: TreeNode<T>?, depth: Int = 0) {
    node ?: return // If the node is null, return

    // Print the current node's value with indentation based on its depth in the tree
    println("--".repeat(depth * 2) + node.element.toString())

    // Recursively print each child, increasing the depth to add indentation
    for (child in node.child) {
        printTree(child, depth + 1)
    }
}