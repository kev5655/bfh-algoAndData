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

    val maxLevel = findMaxDepth(root)
    val nodeQueue: Queue<Pair<TreeNode<T>, Int>> = LinkedList()
    nodeQueue.add(root to 0)

    var currentLevel = 0
    val levelNodes = mutableListOf<String>()

    while (nodeQueue.isNotEmpty()) {
        val (node, level) = nodeQueue.poll()

        // Start a new level if necessary
        if (level != currentLevel) {
            printLevel(levelNodes, currentLevel, maxLevel)
            levelNodes.clear()
            currentLevel = level
        }

        levelNodes.add(node.element.toString())

        // Enqueue child nodes if they exist
        node.left?.also { nodeQueue.add(it to level + 1) }
        node.right?.also { nodeQueue.add(it to level + 1) }
    }

    // Print the last level collected
    printLevel(levelNodes, currentLevel, maxLevel)
}

fun printLevel(levelNodes: List<String>, level: Int, maxLevel: Int) {
    val indentSpace = calculateIndent(level, maxLevel)
    val betweenSpace = calculateBetweenSpace(level, maxLevel)
    val indent = " ".repeat(Math.max(0, indentSpace))
    val spacing = " ".repeat(Math.max(0, betweenSpace))

    println(indent + levelNodes.joinToString(spacing))
}

fun calculateIndent(level: Int, maxLevel: Int): Int {
    return (1 shl (maxLevel - level)) - 1
}

fun calculateBetweenSpace(level: Int, maxLevel: Int): Int {
    return (1 shl (maxLevel - level + 1)) - 1
}

fun <T> findMaxDepth(node: TreeNode<T>?): Int {
    if (node == null) return 0
    val leftDepth = findMaxDepth(node.left)
    val rightDepth = findMaxDepth(node.right)
    return 1 + maxOf(leftDepth, rightDepth)
}