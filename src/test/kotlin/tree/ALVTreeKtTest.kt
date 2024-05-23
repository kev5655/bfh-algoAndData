package tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ALVTreeKtTest {

    @Test
    fun leftRotation() {
        var tree = nodeOf(12, null)
        tree.left = nodeOf(7, tree)
        tree.right = nodeOf(18, tree)
        tree.right!!.right = nodeOf(19, tree.right!!)
        tree.right!!.right!!.right = nodeOf(21, tree.right!!)


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

        val tree1 = nodeOf(7, null)
        tree1.right = nodeOf(12, tree1)
        tree1.right!!.right = nodeOf(18, null)

        leftRotation(tree1)
        assertEquals(12, tree1.element)
        assertEquals(18, tree1.right!!.element)
        assertEquals(7, tree1.left!!.element)

    }

    @Test
    fun insert() {
        val list = listOf(7, 12, 18, 21)
        val fist = list[0]
        val alv = nodeOf(fist, null)
        list.drop(1).forEach { insert(alv, it) }

        assertEquals(12, alv.element)
        assertEquals(7, alv.left!!.element)
        assertEquals(19, alv.right!!.element)
        assertEquals(18, alv.right!!.left!!.element)
        assertEquals(21, alv.right!!.right!!.element)

    }
}