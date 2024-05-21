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
        return "TreeNode(element=$element, parent=$parent)"
    }

    fun deepCopy(parent: ALVTreeNode<T>? = null): ALVTreeNode<T> {
        val newNode = ALVTreeNode(
            element = this.element,
            parent = parent,
            balance = this.balance
        )
        newNode.left = this.left?.deepCopy(newNode)
        newNode.right = this.right?.deepCopy(newNode)
        return newNode
    }


}

fun <T> nodeOf(x: T, parent: ALVTreeNode<T>?): ALVTreeNode<T> {
    return ALVTreeNode(x, parent)
}


fun <T : Comparable<T>> insert(alvTree: ALVTreeNode<T>, element: T): Unit? {
    when {
        element > alvTree.element -> tryInsertRight(alvTree, element)
        element < alvTree.element -> tryInsertLeft(alvTree, element)
        element == alvTree.element -> return null
    }
    if (alvTree.balance == 2) {
        when {
            alvTree.left?.balance == 1 -> {

            }

            alvTree.left?.balance == -1 -> {

            }

            alvTree.right?.balance == 1 -> {
                leftRotation(alvTree)
            }

            alvTree.right?.balance == -1 -> {

            }
        }
    }
    return null
}

fun <T> leftRotation(alvTree: ALVTreeNode<T>) {
    alvTree.right!!.parent = null
    val rightNode = alvTree.right!!.deepCopy()
    alvTree.right = null
    val rootNode = alvTree.deepCopy()
    alvTree.element = rightNode.element
    rightNode.left = rootNode
    alvTree.right = rightNode.right
    rootNode.parent = alvTree
    alvTree.left = rootNode
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

private fun <T : Comparable<T>> tryInsertRight(alvTree: ALVTreeNode<T>, element: T) {
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
    alvTree.balance = (alvTree.right?.let {
        it.balance + 1
    } ?: 0) + (alvTree.left?.let {
        it.balance - 1
    } ?: 0)
    calcBalance(alvTree.parent)
}

//fun <T> calcBalanceDown(alvTree: ALVTreeNode<T>?) {
//    if(alvTree == null) return
//    var balance = 0
//    if(alvTree.right != null) balance++
//    if(alvTree.left != null) balance--
//    alvTree.balance = balance
//    calcBalance(alvTree.parent)
//}

