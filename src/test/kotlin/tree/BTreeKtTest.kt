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
        assertEquals(10, x[5])
        x = insertInList(x, 5)
        assertEquals(5, x[3])
        x = insertInList(x, 7)
        assertEquals(7, x[4])
        x = insertInList(x, 6)
        assertEquals(6, x[3])
        x = insertInList(x, 11)
        assertEquals(11, x[6])
    }
}