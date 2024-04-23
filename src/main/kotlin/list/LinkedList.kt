package list


class LinkedList<T>: List<T> {

    private var dummyNode = Node<T>(null, null)

    override fun get(index: Int): T? {
        var currentNode = dummyNode
        for (i in 0 until index) {
            currentNode = currentNode.next ?: return null
        }
        return currentNode.element
    }

    override fun add(index: Int, element: T) {
        var currentNode = dummyNode
        for (i in 0 until index) {
            if(currentNode.next == null) currentNode.next = Node(null, null)
            currentNode = currentNode.next!!
        }
        currentNode.element = element
    }


    override fun remove(index: Int) {
        if (index < 0) {
            throw IndexOutOfBoundsException("Index $index is out of bounds.")
        }
        var currentNode = dummyNode
        var beforeNode = dummyNode
        for (i in 0 until index) {
            beforeNode = currentNode ?: throw IndexOutOfBoundsException("Index $index is out of bounds.")
            currentNode = currentNode.next!!
        }

        if (currentNode == null) {
            throw IndexOutOfBoundsException("Index $index is out of bounds.")
        }

        beforeNode.next = currentNode.next
    }

    data class Node<T>(var element: T? = null, var next: Node<T>? = null)


}