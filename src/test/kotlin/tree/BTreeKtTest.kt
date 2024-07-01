package tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class BTreeKtTest {


    @Test
    fun insert() {

        val list = listOf(107, 83, 61, 37, 7, 11, 97, 79, 59, 31, 5, 43, 71, 29, 2, 67, 17, 89)
        val tree = nodeOf(list[0])

        list.slice(1 until 3).forEach { insert(tree, it) }
        assertEquals(4, tree.elements)
        intArrayOf(37, 61, 83, 107).forEachIndexed { i: Int, i1: Int -> assertEquals(i1, tree.elements[i]) }
        assertNull(tree.links)


    }

    @Test
    fun insertIntoNode() {
    }

    @Test
    fun insertInList() {
        val list = List(10) { null }

        var x = insertInList(list, 10)
        assertEquals(x, listOf(null, null, null, null, null, 10, null, null, null, null))
        x = insertInList(x, 5)
        assertEquals(x, listOf(null, null, null, 5, null, 10, null, null, null, null))
        assertEquals(5, x[3])
        x = insertInList(x, 7)
        assertEquals(x, listOf(null, null, null, 5, 7, 10, null, null, null, null))
        assertEquals(7, x[4])
        x = insertInList(x, 6)
        assertEquals(x, listOf(null, null, 5, 6, 7, 10, null, null, null, null))
        assertEquals(6, x[3])
        x = insertInList(x, 11)
        assertEquals(x, listOf(null, null, 5, 6, 7, 10, null, 11, null, null))
        assertEquals(11, x[6])
    }
}