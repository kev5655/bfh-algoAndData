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
    child?.takeIf { this.element < it.element }?.let { this.swapWith(it) }
}
fun <T> TreeNode<T>.swapWith(b: TreeNode<T>) {
    this.element = b.element.also { b.element = this.element }
}

fun <T> getLastParents(tree: TreeNode<T>): List<TreeNode<T>> {
    if (tree.left?.left == null &&
        tree.left?.right == null &&
        tree.right?.left == null &&
        tree.right?.right == null
    ) return listOf(tree)
    return getLastParents(tree.left!!) + getLastParents(tree.right!!)
}

fun <T>getLastNodes(tree: TreeNode<T>?): List<TreeNode<T>> {
    if(tree == null) return listOf()
    if(tree.left == null && tree.right == null) return listOf(tree)

    return getLastNodes(tree.right) + getLastNodes(tree.left)
}

fun <T> getLastNodeFromBalancedTree(tree: TreeNode<T>): TreeNode<T> {
    val h = findLeftDepth(tree)
    if(h == 1) return tree

    return if(h - 1 <= findLeftDepth(tree.right)) {
        getLastNodeFromBalancedTree(tree.right!!)
    } else {
        getLastNodeFromBalancedTree(tree.left!!)
    }
}

fun <T> getNodeByLevel(tree: TreeNode<T>?, level: Int, counter: Int = 0): List<TreeNode<T>> {
    assert(level <= findMaxDepth(tree))
    if (tree == null) return listOf()
    if (counter == level) return listOf(tree)

    return getNodeByLevel(tree.left, level, counter + 1) + getNodeByLevel(tree.right, level, counter + 1)
}

fun <T> nodeOf(x: T): TreeNode<T> {
    return TreeNode(x)
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
