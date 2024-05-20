package hash.arcive

interface Dictornary<T> {
    fun insert(x: T)
    fun delete(x: T)
    fun access(x: T): T
}
