package hash

class Data(val index: Int, val h1: Int, h2: Int)

fun main() {

    val list = listOf(14, 21, 27, 28, 8, 18, 15, 36, 5, 2)
        .map { Data(it, hashFn1(it), hashFn2(it)) }




}


private fun hashFn1(x: Int): Int {
    return x % 13
}

private fun hashFn2(x: Int): Int {
    return 1 + (x % 11)
}
