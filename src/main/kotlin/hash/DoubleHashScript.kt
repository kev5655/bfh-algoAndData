package hash

class Data(val value: Int, val h1: Int, val h2: Int)

fun main() {

    val m = 13
    val list = listOf(45, 27, 15, 78, 5, 19, 22, 38, 9, 28, 1)
        .map { Data(it, hashFn1(it), hashFn2(it)) }
    printHashTable(list)

     doubleHashing(list, m).forEach { print(" $it, ") }

}

fun doubleHashing(list: List<Data>, size: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(13)

    list.forEach { data ->
        if(hashArray[data.h1] == null) {
            hashArray[data.h1] = data.value
        } else {
            var found = false
            var current = data.h1
            while (!found) {
                current = (current - data.h2) % size
                if(hashArray[current] == null) {
                    found = true
                    hashArray[current] = data.value
                }
            }
        }
    }

    return hashArray

}


private fun hashFn1(x: Int): Int {
    return x % 13
}

private fun hashFn2(x: Int): Int {
    return 1 + (x % 11)
}

fun printHashTable(data: List<Data>) {
    println("| Index | Hash1 | Hash2 |")
    println("|-------|-------|-------|")

    data.forEach {
        println("| ${it.value.toString().padEnd(6)} | ${it.h1.toString().padEnd(5)} | ${it.h2.toString().padEnd(5)} |")
    }
}
