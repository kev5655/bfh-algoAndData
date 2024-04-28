package sorting.helper

import java.util.*

data class TreeNode<T>(
    var element: T,
    var parent: TreeNode<T>? = null,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null,
) {
    fun deepCopy(parent: TreeNode<T>? = null): TreeNode<T> {
        val newNode = TreeNode(element, parent)
        newNode.left = left?.deepCopy(newNode)
        newNode.right = right?.deepCopy(newNode)
        return newNode
    }
}

fun <T> swapNode(a: TreeNode<T>, b: TreeNode<T>){
    a.element = b.element.also { b.element = a.element }
}

fun <T> getLastParents(tree: TreeNode<T>): List<TreeNode<T>> {
    if(tree.left?.left == null &&
        tree.left?.right == null &&
        tree.right?.left == null &&
        tree.right?.right == null) {
        return listOf(tree)
    }

    return getLastParents(tree.left!!) + getLastParents(tree.right!!)
}


fun <T> nodeOf(x: T): TreeNode<T> {
    return TreeNode(x)
}

fun <T> printTree(root: TreeNode<T>?) {
    if (root == null) return
    val queue: Queue<Pair<TreeNode<T>, Int>> = LinkedList()
    queue.add(root to 0)
    var currentLevel = 0
    var line = StringBuilder()
    val levelNodes = mutableListOf<String>()

    while (queue.isNotEmpty()) {
        val (node, level) = queue.poll()

        // When the level changes, process and print the previous level
        if (level != currentLevel) {
            printLevel(levelNodes, currentLevel)
            levelNodes.clear()
            currentLevel = level
        }

        // Formatting node value as a string
        levelNodes.add("${node.element}")

        // Adding children to the queue with their respective levels
        node.left?.let { queue.add(it to level + 1) }
        node.right?.let { queue.add(it to level + 1) }
    }

    // Print the last level collected
    printLevel(levelNodes, currentLevel)
}

fun printLevel(levelNodes: List<String>, level: Int) {
    // Calculate indent for the current level
    val indent = " ".repeat((1 shl (4 - level)) - 1)
    val betweenSpace = " ".repeat((1 shl (5 - level)) - 1)
    println(indent + levelNodes.joinToString(betweenSpace))
}