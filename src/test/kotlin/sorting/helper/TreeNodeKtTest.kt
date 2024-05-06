package sorting.helper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class TreeNodeKtTest {

    @Test
    fun changeNode() {
        val tree = createTreeBySiftUp(intArrayOf(5, 2, 4, 6, 3, 8, 10, 12, 44, 1), null)
        //                5
        //       2               4
        //   6       3       8       10
        // 12   44   1
        printTree(tree)
        assertEquals(5, tree?.element)
        assertEquals(2, tree?.left?.element)

        assertNotNull(tree)
        val swaped5And2 = tree.deepCopy()
        swap5And2(swaped5And2)
        println("Tree after swap 5 and 2")
        printTree(swaped5And2)
        assertEquals(2, swaped5And2.element)
        assertEquals(5, swaped5And2.left?.element)

        val swaped10And1 = tree.deepCopy()
        swap10And1(swaped10And1)
        println("Tree after swap 10 and 1")
        printTree(swaped10And1)
        assertEquals(1, swaped10And1.right?.right?.element)
        assertEquals(10, swaped10And1.left?.right?.left?.element)


    }

    @Test
    fun getLastParents() {
        val tree = createTreeBySiftUp(
            intArrayOf(
                67,
                85,
                13,
                38,
                37,
                76,
                42,
                97,
                93,
                69,
                24,
                22,
                21,
                53,
                3,
                52,
                86,
                20,
                19,
                9,
                61,
                96,
                1,
                83,
                72,
                71,
                87,
                30,
                17,
                56,
                80,
                40,
                6,
                51
            ), null
        )
        assertNotNull(tree)
        printTree(tree)
        getLastParents(tree).forEach { println("Tree with root: ${it.element}"); printTree(it) }

    }

    @Test
    fun verifyLastNodeValueInFullBinaryTree() {
        val array = intArrayOf(10, 3, 26, 9, 5, 31, 88, 45, 90, 89, 12, 43, 67, 32, 44)
        val tree = createTreeBySiftUp(array, null)
        printTree(tree)
        val node = getLastNodeFromBalancedTree(tree!!)

        assertEquals(44, node.element)
    }

    @Test
    fun `ensureLastRightNodeIsLeafInBinaryTree`() {
        val array = intArrayOf(10, 3, 26, 9, 5, 31, 88, 45, 90, 89, 12, 43, 67)
        val tree = createTreeBySiftUp(array, null)
        printTree(tree)
        val node = getLastNodeFromBalancedTree(tree!!)

        assertEquals(67, node.element)
    }

    @Test
    fun `simpleBinaryTreeNotFull`() {
        val array = intArrayOf(10, 3, 26, 9, 5)
        val tree = createTreeBySiftUp(array, null)
        printTree(tree)
        val node = getLastNodeFromBalancedTree(tree!!)

        assertEquals(5, node.element)
    }



    private fun swap5And2(tree: TreeNode<Int>) = tree.swapWith(tree.left!!)
    private fun swap10And1(tree: TreeNode<Int>) = tree.right?.right!!.swapWith(tree.left?.right?.left!!)
}