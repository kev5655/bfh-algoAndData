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

    fun destroy() {
        if (this == parent?.left) {
            parent?.left = null
        } else if (this == parent?.right) {
            parent?.right = null
        }
        parent = null
        left = null
        right = null
    }

    override fun toString(): String {
        return "TreeNode(element=$element, parent=$parent)"
    }

}

fun TreeNode<Int>.swapGreater(child: TreeNode<Int>?) {
    child?.takeIf { this.element < it.element }?.let { swapNode(this, it) }
}

fun <T> swapNode(a: TreeNode<T>, b: TreeNode<T>) {
    a.element = b.element.also { b.element = a.element }
}

fun <T> getLastParents(tree: TreeNode<T>): List<TreeNode<T>> {
    if (tree.left?.left == null &&
        tree.left?.right == null &&
        tree.right?.left == null &&
        tree.right?.right == null
    ) {
        return listOf(tree)
    }

    return getLastParents(tree.left!!) + getLastParents(tree.right!!)
}

//fun <T>getLastNode(tree: TreeNode<T>?): List<TreeNode<T>> {
//    if(tree == null) return listOf()
//    if(tree.left == null && tree.right == null) return listOf(tree)
//
//    return getLastNode(tree.right) + getLastNode(tree.left)
//}

fun <T> getLastNode(tree: TreeNode<T>): TreeNode<T> {
    val h = findLeftDepth(tree)
    if(h == 1) {
        return tree
    }

    val x = findLeftDepth(tree.right)
    return if(h - 1 <= x) {
        getLastNode(tree.right!!)
    } else {
        getLastNode(tree.left!!)
    }
}

fun <T> getNodeByLevel(tree: TreeNode<T>?, level: Int, counter: Int = 0): List<TreeNode<T>> {
    assert(level <= findMaxDepth(tree))
    if (tree == null) return listOf()
    if (counter == level) {
        return listOf(tree)
    }

    return getNodeByLevel(tree.left, level, counter + 1) + getNodeByLevel(tree.right, level, counter + 1)
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

fun <T> findLeftDepth(node: TreeNode<T>?): Int {
    if(node == null) return 0
    return 1 + findLeftDepth(node.left)
}

fun <T> findMaxDepth(node: TreeNode<T>?): Int {
    if (node == null) return 0
    val leftDepth = findMaxDepth(node.left)
    val rightDepth = findMaxDepth(node.right)
    return 1 + maxOf(leftDepth, rightDepth)
}



fun <T> getLastNode(tree: TreeNode<T>, level: Int = 0, maxLevel: Int = 0): TreeNode<T> {
    val newMaxLevel = maxOf(level, maxLevel)

    if (tree.left == null && tree.right == null) {
        println("I was on: ${tree.element} level: $level, maxLevel: $newMaxLevel")
        return tree.parent!!
    }

    val parent1 = getLastNode(tree.right!!, level + 1, newMaxLevel)
    println("Return Parent 1: ${parent1.element}, current element ${tree.element} level: $level maxLevel: $newMaxLevel")
    val parent2 = getLastNode(tree.left!!, level + 1, newMaxLevel)
    println("Return Parent 2: ${parent2.element}, current element ${tree.element} level: $level maxLevel: $newMaxLevel")

    return parent2.left ?: parent2
}

//fun <T> getLastNode(
//    tree: TreeNode<T>,
//    bestNode: Pair<Int, TreeNode<T>>, level: Int = 0
//):
//        Pair<TreeNode<T>, Pair<Int, TreeNode<T>>> {
//
//    var nextBestNode: Pair<Int, TreeNode<T>> = bestNode
//    if (tree.right == null && tree.left == null) {
//        nextBestNode = if (level > bestNode.first) {
//            Pair(level, tree)
//        } else {
//            bestNode
//        }
//        println("I was on ${tree.element} level: $level bestNode: $nextBestNode")
//        return Pair(tree.parent!!, nextBestNode)
//    }
//
//    val parent = getLastNode(tree.right!!, nextBestNode, level + 1)
//
//    getLastNode(tree.left!!, nextBestNode, level + 1)
//
//    return Pair(parent.first, nextBestNode)
//
////    return nextBestNode?.second ?: bestNode.second
//}

//fun <T> getLastNode(tree: TreeNode<T>): Pair<Boolean, TreeNode<T>> {
//    if (tree.right == null && tree.left == null) {
//        println("I was on ${tree.element}")
//        return Pair(false, tree.parent!!)
//    }
//    if (tree.right == null) {
//        return Pair(true, tree.left!!)
//    }
//
//    val parent = getLastNode(tree.right!!)
//    if(parent.first) return parent
//
//    return getLastNode(tree.left!!)
//}


//fun <T> getLastLeft(tree: TreeNode<T>): TreeNode<T> {
//    if (tree.left == null) return tree
//
//    return getLastNode(tree.left!!)
//}