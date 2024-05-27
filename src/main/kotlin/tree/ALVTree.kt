package tree

// https://www.youtube.com/watch?v=EyrMyI3HG1g
fun main() {
    val list = listOf(7, 12, 18, 21, 19, 34)
    val fist = list[0]
    val alv = nodeOf(fist, null)
    list.drop(1).forEach { insert(alv, it) }
}

data class ALVTreeNode<T>(
    var element: T,
    var parent: ALVTreeNode<T>? = null,
    var left: ALVTreeNode<T>? = null,
    var right: ALVTreeNode<T>? = null,
    var balance: Int = 0,
) {
    override fun toString(): String {
        return "TreeNode(element=$element, parent=${parent?.element ?: "null"}," +
                " left=${left?.element ?: "null"}, right=${right?.element ?: "null"})"
    }

    fun deepCopy(parent: ALVTreeNode<T>? = null): ALVTreeNode<T> {
        val newNode = ALVTreeNode(
            element = this.element,
//            parent = parent,
            balance = this.balance
        )
        newNode.parent = parent
        newNode.left = this.left?.deepCopy(newNode)
        newNode.right = this.right?.deepCopy(newNode)
        return newNode
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ALVTreeNode<*>

        if (element != other.element) return false
        if (balance != other.balance) return false

        // Compare references for parent, left, and right nodes
        if (parent !== other.parent) return false
        if (left !== other.left) return false
        if (right !== other.right) return false

        return true
    }

    override fun hashCode(): Int {
        var result = element?.hashCode() ?: 0
        result = 31 * result + (parent?.let { System.identityHashCode(it) } ?: 0)
        result = 31 * result + (left?.let { System.identityHashCode(it) } ?: 0)
        result = 31 * result + (right?.let { System.identityHashCode(it) } ?: 0)
        result = 31 * result + balance
        return result
    }
}

fun <T> nodeOf(x: T, parent: ALVTreeNode<T>?): ALVTreeNode<T> {
    return ALVTreeNode(x, parent)
}


fun <T : Comparable<T>> insert(alvTree: ALVTreeNode<T>, element: T) {
    when {
        element > alvTree.element -> tryInsertRight(alvTree, element)
        element < alvTree.element -> tryInsertLeft(alvTree, element)
        element == alvTree.element -> return
    }
    when (alvTree.balance) {
        2 -> when (alvTree.right?.balance) {
            1 -> leftRotation(alvTree)
            -1 -> {
                rightRotation(alvTree.right!!)
                leftRotation(alvTree)
            }
        }
        -2 -> when (alvTree.left?.balance) {
            -1 -> rightRotation(alvTree)
            1 -> {
                leftRotation(alvTree.left!!)
                rightRotation(alvTree)
            }
        }
    }

    return
}

fun <T> leftRotation(alvTree: ALVTreeNode<T>) {
    val rootRoot = alvTree.parent

    val root = alvTree.right!!
    root.parent = null
    root.balance = 0
    val saveLeft = root.left?.deepCopy()
    saveLeft?.parent = null

    alvTree.parent = null
    alvTree.balance = 0
    alvTree.right = saveLeft
    val alvTreeCopy = alvTree.deepCopy()
    root.left = alvTreeCopy

    alvTree.left = root.left?.deepCopy()
    alvTree.right = root.right
    alvTree.element = root.element
    alvTree.balance = 0

    alvTree.parent = rootRoot
    alvTree.left?.parent = alvTree
    alvTree.left?.right?.parent = alvTree.left
    alvTree.right?.parent = alvTree
}

fun <T> rightRotation(alvTree: ALVTreeNode<T>) {
    val rootRoot = alvTree.parent

    val root = alvTree.left!!
    root.parent = null
    root.balance = 0
    val saveRight = root.right?.deepCopy()
    saveRight?.parent = null

    alvTree.parent = null
    alvTree.balance = 0
    alvTree.left = saveRight
    val alvTreeCopy = alvTree.deepCopy()
    root.right = alvTreeCopy

    alvTree.left = root.left
    alvTree.right = root.right?.deepCopy()
    alvTree.element = root.element
    alvTree.balance = 0

    alvTree.parent = rootRoot
    alvTree.right?.parent = alvTree
    alvTree.right?.left?.parent = alvTree.right
    alvTree.left?.parent = alvTree
}

private fun <T> writeBack(left: ALVTreeNode<T>, root: ALVTreeNode<T>, alvTree: ALVTreeNode<T>) {
    left.parent = root
    left.balance = 0

    alvTree.left = root.left
    alvTree.right = root.right
    alvTree.parent = root.parent
    alvTree.element = root.element
    alvTree.balance = 0
}

private fun <T : Comparable<T>> tryInsertLeft(alvTree: ALVTreeNode<T>, element: T) {
    val leftNode = alvTree.left
    if (leftNode == null) {
        alvTree.left = nodeOf(element, alvTree)
        calcBalance(alvTree)
    } else {
        insert(leftNode, element)
    }
}

fun <T : Comparable<T>> tryInsertRight(alvTree: ALVTreeNode<T>, element: T) {
    val rightNode = alvTree.right
    if (rightNode == null) {
        alvTree.right = nodeOf(element, alvTree)
        calcBalance(alvTree)
    } else {
        insert(rightNode, element)
    }
}

fun <T> calcBalance(alvTree: ALVTreeNode<T>?) {
    if (alvTree == null) return

    alvTree.balance = depth(alvTree.right) - depth(alvTree.left)

    if (alvTree.balance == 2 || alvTree.balance == -2) return
    calcBalance(alvTree.parent)
}

private fun <T> depth(alvTree: ALVTreeNode<T>?): Int {
    if (alvTree == null) return 0
    val right = alvTree.right
    val left = alvTree.left

    return if (right != null) {
        1 + depth(right)
    } else if (left != null) {
        1 + depth(left)
    } else {
        1
    }
}








