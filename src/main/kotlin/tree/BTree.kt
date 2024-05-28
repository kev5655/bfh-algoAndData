package tree

// https://www.youtube.com/watch?v=K1a2Bk8NrYQ
object DEFAULT {
    val MAX_SIZE = 4
}

data class BTree<T>(
    val elements: Array<T>,
    val parent: BTree<T>? = null,
    val links: List<BTree<T>>? = null,
)

inline fun <reified T> nodeOf(element: T): BTree<T> {
    val elements = emptyArray<T>()
    elements[0] = element
    return BTree(elements)

}


fun main() {
    val list = listOf(107, 83, 61, 37, 7, 11, 97, 79, 59, 31, 5, 43, 71, 29, 2, 67, 17, 89)
    val map = hashMapOf<String, Int>()
}

fun <T: Comparable<T>> insert(x: T) {

}