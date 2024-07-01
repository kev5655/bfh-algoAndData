package hash

import org.junit.jupiter.api.Test
import java.lang.Math.floorMod

class OrderedHashingKtTest {

    private fun hashFn1(x: Int): Int {
        val y = floorMod(x, 13) // !!!UPDATE HASH FUNCTION
        return y
    }

    private fun hashFn2(x: Int): Int {
        val y = 1 + floorMod(x, 11)
        return y
    }

    @Test
    fun example() {
        val m = 13 // !!!UPDATE TABLE_SIZE !!! 0 is included
        val list = listOf(14, 21, 27, 28, 8, 18, 15, 36, 5, 2) // !!!UPDATE KEY'S
            .map { calcHash(it, ::hashFn1, ::hashFn2) }
        printHashTable(list)

        val result = orderedHashing(list, m)

        val expected = listOf(
            28, 14, 2, null, null, 5, 36, 18, 8, 27, 15, null, 21
        )
        expected.forEachIndexed { index, value ->
            println("$index: $value == ${result[index]}")
            assert(value == result[index])
        }
    }
}