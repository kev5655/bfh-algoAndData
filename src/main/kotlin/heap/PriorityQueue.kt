package heap

import sorting.helper.TreeNode
import sorting.helper.nodeOf

class PriorityQueue<T>(init: T) : PriorityQueueIT<T> {

    var data: TreeNode<T>? = nodeOf(init)

    override fun <T> insert(priority: Int, item: T) {
        TODO("Not yet implemented")
    }

    override fun <T> accessMin(): T {
        TODO("Not yet implemented")
    }

    override fun deleteMin() {
        TODO("Not yet implemented")
    }

    override fun <T> decreaseKey(priority: Int, item: T) {
        TODO("Not yet implemented")
    }

    override fun <T> delete(item: T) {
        TODO("Not yet implemented")
    }
}