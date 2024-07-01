package hash

import java.lang.Math.floorMod

class Data(val value: Int, val h1: Int, val h2: Int)

private fun hashFn1(x: Int): Int {
//    val x = x % 13 // !!!UPDATE HASH FUNCTION
    val x = floorMod(x, 11)
    return x
}

private fun hashFn2(x: Int): Int {
//    val x = 1 + (x % 11) // !!!UPDATE HASH FUNCTION
    val x = 1 + (floorMod(x, 9))
    return x
}


fun main() {
    val m = 11 // !!!UPDATE TABLE_SIZE !!! 0 is included
    val list = listOf(65, 33, 5, 19, 22, 15, 27, 12, 28, 1) // !!!UPDATE KEY'S
        .map { calcHash(it, ::hashFn1, ::hashFn2) }
    printHashTable(list)

    doubleHashing(list, m).forEach { print(" $it, ") }
}

fun doubleHashing(list: List<Data>, tableSize: Int): Array<Int?> {
    val hashArray: Array<Int?> = arrayOfNulls(tableSize)

    list.forEach { data ->
        println("Insert: ${data.value} | " + hashArray.joinToString { x -> "${x}, " })
        if (hashArray[data.h1] == null) {
            hashArray[data.h1] = data.value
        } else {
            var attempts = 0
            var current = data.h1
            while (true) {
                current = (current - data.h2 + tableSize) % tableSize
                if (hashArray[current] == null) {
                    hashArray[current] = data.value
                    break
                }
                attempts++
                if (attempts >= tableSize) { // Guard against infinite loops
                    throw IllegalStateException("Hash table is full or no empty slot found due to improper hash function.")
                }
            }
        }
    }

    return hashArray
}

fun calcHash(number: Int, hashFn1: (x: Int) -> Int, hashFn2: (x: Int) -> Int): Data {
    return Data(number, hashFn1(number), hashFn2(number))
}

fun printHashTable(data: List<Data>) {
    println("| Index | Hash1 | Hash2 |")
    println("|-------|-------|-------|")

    data.forEach {
        println("| ${it.value.toString().padEnd(6)} | ${it.h1.toString().padEnd(5)} | ${it.h2.toString().padEnd(5)} |")
    }
}
