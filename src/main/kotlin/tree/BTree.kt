package tree

// https://www.youtube.com/watch?v=K1a2Bk8NrYQ
object DEFAULT {
    val MAX_SIZE = 4
}

data class BTree<T>(
    var elements: List<T?>,
    val parent: BTree<T>? = null,
    var links: List<BTree<T>>? = null,
)

fun <T> nodeOf(element: T): BTree<T> {
    val elements = MutableList<T?>(DEFAULT.MAX_SIZE + 1) { null }
    elements[0] = element
    return BTree(elements)
}

fun <T> nodeOf(elements: List<T>): BTree<T> {
    assert(elements.size > DEFAULT.MAX_SIZE) { System.err.println("elements don't match expected size, elements: $elements") }
    return BTree(elements)
}


fun main() {
    val list = listOf(107, 83, 61, 37, 7, 11, 97, 79, 59, 31, 5, 43, 71, 29, 2, 67, 17, 89)
    val tree = nodeOf(list[0])

    list.forEach { insert(tree, it) }
}

fun <T : Comparable<T>> insert(bTree: BTree<T>, x: T) {

    if (bTree.links == null) insertIntoNode(bTree, x)
    else {

    }

}

fun <T : Comparable<T>> insertIntoNode(bTree: BTree<T>, x: T): Triple<BTree<T>, BTree<T>, BTree<T>>? {
    val list = bTree.elements.toMutableList()

    var index = list.size / 2

    while (true) {
        when {
            list[index] == null -> {
                list[index] = x
                break
            }

            list[index]!! > x -> index -= index / 2
            list[index]!! < x -> index -= index / 2
        }
    }
    return if (list.size >= DEFAULT.MAX_SIZE) {
        val mid = list.size / 2
        val left = list.slice(0 until mid).filterNotNull()
        val right = list.slice(mid + 1 until list.size).filterNotNull()
        val bTreeLeft = nodeOf(left)
        val bTreeRight = nodeOf(right)
        bTree.elements = listOf(list[mid])
        bTree.links = listOf(bTreeLeft, bTreeRight)
        return Triple(bTree, bTreeLeft, bTreeRight)
    } else {
        bTree.elements = list
        null
    }

}