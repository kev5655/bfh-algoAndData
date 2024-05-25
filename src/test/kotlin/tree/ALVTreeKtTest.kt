package tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ALVTreeKtTest {

    @Test
    fun testRotation() {
        var tree = nodeOf(12, null)
        tree.left = nodeOf(7, tree)
        tree.right = nodeOf(18, tree)
        tree.right!!.right = nodeOf(21, tree.right!!)
        tree.right!!.right!!.left = nodeOf(19, tree.right!!.right)

        rightRotation(tree.right!!.right!!)
        assertEquals(12, tree.element)
        assertEquals(7, tree.left!!.element)
        assertEquals(18, tree.right!!.element)
        assertEquals(19, tree.right!!.right!!.element)
        assertEquals(21, tree.right!!.right!!.right!!.element)

        leftRotation(tree.right!!)

        assertEquals(12, tree.element)
        assertEquals(7, tree.left!!.element)
        assertEquals(19, tree.right!!.element)
        assertEquals(21, tree.right!!.right!!.element)
        assertEquals(18, tree.right!!.left!!.element)

        tree.right!!.right!!.right = nodeOf(34, tree.right!!.right)

        leftRotation(tree)

        assertEquals(19, tree.element)
        assertEquals(12, tree.left!!.element)
        assertEquals(7, tree.left!!.left!!.element)
        assertEquals(18, tree.left!!.right!!.element)
        assertEquals(21, tree.right!!.element)
        assertEquals(34, tree.right!!.right!!.element)
    }

    @Test
    fun leftRotation() {
        val tree = nodeOf(7, null)
        tree.right = nodeOf(12, tree)
        tree.right!!.right = nodeOf(18, null)

        leftRotation(tree)
        assertEquals(12, tree.element)
        kotlin.test.assertNotNull(tree.right!!.parent)
        assertEquals(18, tree.right!!.element)
        kotlin.test.assertNotNull(tree.left!!.parent)
        assertEquals(7, tree.left!!.element)

        val tree1 = nodeOf(20, null)
        tree1.left = nodeOf(10, tree1)
        tree1.right = nodeOf(29, tree1)
        tree1.right!!.right = nodeOf(30, tree1.right)
        tree1.right!!.right!!.right = nodeOf(31, tree1.right!!.right)
        tree1.right!!.left = nodeOf(27, tree1.right!!)

        leftRotation(tree1)

        assertEquals(29, tree1.element)
        assertNotNull(tree1.right!!.parent)
        assertEquals(30, tree1.right!!.element)
        assertNotNull(tree1.right!!.right!!.parent)
        assertEquals(31, tree1.right!!.right!!.element)
        assertNotNull(tree1.left!!.parent)
        assertEquals(20, tree1.left!!.element)
        assertNotNull(tree1.left!!.right!!.parent)
        assertEquals(27, tree1.left!!.right!!.element)
        assertNotNull(tree1.left!!.left!!.parent)
        assertEquals(10, tree1.left!!.left!!.element)
    }

    @Test
    fun rightRotation() {
        val tree = nodeOf(29, null)
        tree.right = nodeOf(30, tree)
        tree.right!!.right = nodeOf(31, tree.right)

        tree.left = nodeOf(26, tree)
        tree.left!!.right = nodeOf(27, tree.left)
        tree.left!!.right!!.right = nodeOf(28, tree.left!!.right)
        tree.left!!.left = nodeOf(20, tree.left)
        tree.left!!.left!!.right = nodeOf(25,  tree.left!!.left)
        tree.left!!.left!!.left = nodeOf(10, tree.left!!.left)
        tree.left!!.left!!.left!!.left = nodeOf(9, tree.left!!.left!!.left)

        rightRotation(tree)
        assertEquals(26, tree.element)
        assertEquals(29, tree.right!!.element)
        assertNotNull(tree.right!!.parent)
        assertEquals(30, tree.right!!.right!!.element)
        assertNotNull(tree.right!!.right!!.parent)
        assertEquals(31, tree.right!!.right!!.right!!.element)
        assertNotNull(tree.right!!.left!!.parent)
        assertEquals(27, tree.right!!.left!!.element)
        assertNotNull(tree.right!!.left!!.right!!.parent)
        assertEquals(28, tree.right!!.left!!.right!!.element)

        assertNotNull(tree.left!!.parent)
        assertEquals(20, tree.left!!.element)
        assertNotNull(tree.left!!.right!!.parent)
        assertEquals(25, tree.left!!.right!!.element)
        assertNotNull(tree.left!!.left!!.parent)
        assertEquals(10, tree.left!!.left!!.element)
        assertNotNull(tree.left!!.left!!.left!!.parent)
        assertEquals(9, tree.left!!.left!!.left!!.element)

        val tree1 = nodeOf(29, null)
        tree1.right = nodeOf(30, tree)
        tree1.right!!.right = nodeOf(31, tree1.right)
        tree1.left = nodeOf(20, tree1)
        tree1.left!!.right = nodeOf(27, tree1.left)
        tree1.left!!.right!!.left = nodeOf(26, tree1.left!!.right)
        tree1.left!!.right!!.left!!.left = nodeOf(25, tree1.left!!.right!!.left)
        tree1.left!!.left = nodeOf(10, tree1.left)

        rightRotation(tree1.left!!.right!!)

        assertEquals(29, tree1.element)
        assertNotNull(tree1.right!!.parent)
        assertEquals(30, tree1.right!!.element)
        assertNotNull(tree1.right!!.right!!.parent)
        assertEquals(31, tree1.right!!.right!!.element)

        assertNotNull(tree1.left!!.parent)
        assertEquals(20, tree1.left!!.element)
        assertNotNull(tree1.left!!.right!!.parent)
        assertEquals(26, tree1.left!!.right!!.element)
        assertNotNull(tree1.left!!.right!!.right!!.parent)
        assertEquals(27, tree1.left!!.right!!.right!!.element)
        assertNotNull(tree1.left!!.right!!.left!!.parent)
        assertEquals(25, tree1.left!!.right!!.left!!.element)
        assertNotNull(tree1.left!!.left!!.parent)
        assertEquals(10, tree1.left!!.left!!.element)


    }



    @Test
    fun insert() {
        val list = listOf(7, 12, 18, 21, 19, 34)
        val fist = list[0]
        val alv = nodeOf(fist, null)

        insert(alv, list[1]) // 12
        assertEquals(7, alv.element)
        assertEquals(12, alv.right!!.element)

        insert(alv, list[2]) // 18
        assertEquals(12, alv.element)
        assertEquals(7, alv.left!!.element)
        assertEquals(18, alv.right!!.element)

        insert(alv, list[3]) // 21
        assertEquals(12, alv.element)
        assertEquals(7, alv.left!!.element)
        assertEquals(18, alv.right!!.element)
        assertEquals(21, alv.right!!.right!!.element)

        insert(alv, list[4]) // 19
        assertEquals(12, alv.element)
        assertNotNull(alv.left!!.parent)
        assertEquals(7, alv.left!!.element)
        assertNotNull(alv.right!!.parent)
        assertEquals(19, alv.right!!.element)
        assertNotNull(alv.right!!.left!!.parent)
        assertEquals(18, alv.right!!.left!!.element)
        assertNotNull(alv.right!!.right!!.parent!!)
        assertEquals(alv.right!!.element, alv.right!!.right!!.parent!!.element)
        assertNotNull(alv.right!!.right!!.parent)
        assertEquals(21, alv.right!!.right!!.element)

        insert(alv, list[5]) // 34
        assertEquals(19, alv.element)
        assertEquals(21, alv.right!!.element)
        assertEquals(34, alv.right!!.right!!.element)
        assertEquals(12, alv.left!!.element)
        assertEquals(18, alv.left!!.right!!.element)
        assertEquals(7, alv.left!!.left!!.element)

//
//        assertEquals(12, alv.element)
//        assertEquals(7, alv.left!!.element)
//        assertEquals(19, alv.right!!.element)
//        assertEquals(18, alv.right!!.left!!.element)
//        assertEquals(21, alv.right!!.right!!.element)
    }


}