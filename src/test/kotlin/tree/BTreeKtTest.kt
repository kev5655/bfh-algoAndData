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
        assertEquals(10, x[0])

        x = insertInList(x, 5)
        assertEquals(5, x[0])
        assertEquals(10, x[1])

        x = insertInList(x, 7)
        assertEquals(5, x[0])
        assertEquals(7, x[1])
        assertEquals(10, x[2])


        x = insertInList(x, 6)
        assertEquals(5, x[0])
        assertEquals(6, x[1])
        assertEquals(7, x[2])
        assertEquals(10, x[3])

        x = insertInList(x, 11)
        assertEquals(5, x[0])
        assertEquals(6, x[1])
        assertEquals(7, x[2])
        assertEquals(10, x[3])
        assertEquals(11, x[4])

    }

    @Test
    fun insertInList_String() {
        val stringList: List<String?> = listOf("apple", "orange", null, "banana")
        val stringX = "grape"
        val stringResult = insertInList(stringList, stringX)
        println(stringResult) // Expected Output: [apple, banana, grape, orange, null]

        // Additional tests
        val testCases = listOf(
            Pair(listOf<String?>("dog", "cat", "elephant", null), "bird") to listOf("bird", "cat", "dog", "elephant", null),
            Pair(listOf<String?>("x", "y", "z", null), "a") to listOf("a", "x", "y", "z", null),
            Pair(listOf<String?>("alpha", "beta", "gamma", null), "delta") to listOf("alpha", "beta", "delta", "gamma", null)
        )

        for ((input, expected) in testCases) {
            val (list, element) = input
            val result = insertInList(list, element)
            println("Input: $list, Element: $element -> Result: $result, Expected: $expected")
            assert(result == expected) { "Test failed for input: $list and element: $element. Expected $expected but got $result" }
        }
    }
}