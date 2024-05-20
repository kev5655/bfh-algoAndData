package hash.arcive


class DoubleHash<T>: Dictornary<T> {
    override fun insert(x: T) {
        TODO("Not yet implemented")
    }

    override fun delete(x: T) {
        TODO("Not yet implemented")
    }

    override fun access(x: T): T {
        return x
    }

}

private fun hashFn1(x: Int): Int {
    return x % 13
}

private fun hashFn2(x: Int): Int {
    return 1 + (x % 11)
}
