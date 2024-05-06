package sorting.helper

import java.util.*

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

    printLevel(levelNodes, currentLevel, maxLevel)
}

fun <T> printTreeNormal(root: TreeNode<T>?) {
    if (root == null) return
    println(root)

    printTreeNormal(root.left)
    printTreeNormal(root.right)
    return
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