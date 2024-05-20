package serach

fun main() {
    val list = listOf(23, 3, 32, 6, 357, 1, 7, 37, 2, 8)
    println(sequenzielleSuche(list, 23))
    println(sequenzielleSuche(list, 33))
}

fun sequenzielleSuche(list: List<Int>, value: Int): Int? {
    for (x in list) {
        if (x == value) {
            return x
        }
    }
    return null
}