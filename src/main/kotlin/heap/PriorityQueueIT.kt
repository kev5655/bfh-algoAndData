package heap

interface PriorityQueueIT<T> {

    // Exercise a)
    fun <T>insert(priority: Int, item: T)
    fun <T>accessMin(): T
    fun deleteMin()

    // Exercise b)
    fun <T>decreaseKey(priority: Int, item: T)
    fun <T>delete(item: T)

}