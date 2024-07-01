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
    val elements = MutableList<T?>(DEFAULT.MAX_SIZE) { null }
    elements[DEFAULT.MAX_SIZE / 2] = element
    return BTree(elements)
}

fun <T> nodeOf(elements: List<T>): BTree<T> {
    assert(elements.size < DEFAULT.MAX_SIZE) { System.err.println("elements don't match expected size, elements: $elements") }
    return BTree(elements)
}


fun main() {
    val list = listOf(107, 83, 61, 37, 7, 11, 97, 79, 59, 31, 5, 43, 71, 29, 2, 67, 17, 89)
    val tree = nodeOf(list[0])

    list.forEach { insert(tree, it) }
}

fun <T : Comparable<T>> insert(bTree: BTree<T>, x: T) {

    val links = bTree.links
    if (links == null) {
        insertIntoNode(bTree, x)
    } else {
        insert(findLink(bTree, x, links), x)
    }

}

private fun <T : Comparable<T>> findLink(
    bTree: BTree<T>,
    x: T,
    links: List<BTree<T>>
): BTree<T> {
    val elements = bTree.elements.filterNotNull()
    for (i in elements.indices) {
        if (x < elements[i]) {
            return links[i]
        }
    }
    return links.last()
}

fun <T : Comparable<T>> insertIntoNode(bTree: BTree<T>, x: T) {
//    val list = bTree.elements.toMutableList()

    val list = insertInList(bTree.elements, x)


    if (list.filterNotNull().size >= DEFAULT.MAX_SIZE) {
        val mid = list.size / 2
        val left = nodeOf(list.slice(0 until mid).filterNotNull())
        val right = nodeOf(list.slice(mid + 1 until list.size).filterNotNull())
        bTree.elements = listOf(list[mid])
        bTree.links = listOf(left, right)
    } else {
        bTree.elements = list
    }

}

fun <T : Comparable<T>> insertInList(list: List<T?>, x: T): List<T?> {
    val mutableList = list.toMutableList()
    mutableList.add(x)
    mutableList.sortWith(compareBy(nullsLast()) { it })
    return mutableList.toList()
}