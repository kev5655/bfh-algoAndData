package list

interface List<T> {

    fun get(index: Int): T?
    fun add(index: Int, element: T)
    fun remove(index: Int)

}