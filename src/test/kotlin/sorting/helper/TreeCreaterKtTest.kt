package sorting.helper

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class TreeCreationTest {

    @Test
    fun testEmptyArray() {
        val array = intArrayOf()
        val result = createTreeBySiftUp(array, null)
        assertNull(result, "Tree should be null for empty array")
    }

    @Test
    fun testSingleElement() {
        val array = intArrayOf(1)
        val result = createTreeBySiftUp(array, null)
        assertNotNull(result)
        assertEquals(1, result?.element, "Tree root should have the single element element")
    }

    @Test
    fun testMultipleElements() {
        val array = intArrayOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val result = createTreeBySiftUp(array, null)
        printTree(result)
        assertNotNull(result)
        assertEquals(10, result?.element, "Root value should be the first element of the array")
        // Checking structure integrity; this test can be extended based on expected tree structure
        assertNotNull(result?.left, "Left subtree should not be null")
        assertEquals(9, result?.left?.element)
        assertEquals(7, result?.left?.left?.element)
        assertEquals(3, result?.left?.left?.left?.element)
        assertNotNull(result?.right, "Right subtree should not be null")
        assertEquals(8, result?.right?.element)
        assertEquals(4, result?.right?.right?.element)
        assertNull(result?.right?.right?.right?.element)

        assertEquals(1, result?.left?.right?.left?.element)

    }

    // Additional detailed tests can be written to verify the exact structure of the tree
    // This would involve writing a function to validate the tree properties (e.g., max-heap property)
}