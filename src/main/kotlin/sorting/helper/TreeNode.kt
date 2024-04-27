package sorting.helper

data class TreeNode<T>(
    var element: T,
    var parent: TreeNode<T>? = null,
    var child: MutableList<TreeNode<T>> = mutableListOf()
) {
    fun deepCopy(): TreeNode<T> {
        val newNode = TreeNode(element, parent)  // Parent is usually not deep-copied to avoid circular references
        newNode.child = child.map { it.deepCopy() }.toMutableList()
        for (child in newNode.child) {
            child.parent = newNode  // Ensuring the new children point back to the new node
        }
        return newNode
    }
}


fun <T> changeNode(node: TreeNode<T>, newParent: TreeNode<T>) {
    val saveNode = node.deepCopy()
    node.element = newParent.element
    node.parent = newParent.parent
    node.child = newParent.child
    newParent.element = saveNode.element
    newParent.parent = saveNode.parent
    newParent.child = saveNode.child
}

fun <T> getFistLastNode(treeNode: TreeNode<T>): TreeNode<T> {
    if (treeNode.child.isEmpty()) {
        return treeNode
    }
    return getFistLastNode(treeNode.child[0])
}

fun <T> getLastNodes(treeNode: TreeNode<T>): List<T> {
    val lastNode = mutableListOf<T>()

    if (treeNode.child.isEmpty()) {
        return lastNode
    }
    return treeNode.child.map { getLastNodes(it) }.flatMap { (a, b) -> listOf(a, b) }
}


fun <T> nodeOf(x: T): TreeNode<T> {
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