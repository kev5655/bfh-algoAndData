package list

fun main() {

    val linkedList = LinkedList<Int>()

    linkedList.add(0, 1)

    for (i in 0 until 10) {
        linkedList.add(i, Math.pow(2.0, i.toDouble()).toInt())
    }

    for (i in 0 until 10) {
        println(linkedList.get(i))
    }
    println("=========================")

    linkedList.remove(1)
    for (i in 0 until 10) {
        println(linkedList.get(i))
    }
    println("=========================")

    val dynamicArray = DynamicArray<Int>(emptyArray())

    for (i in 0 until 10) {
        dynamicArray.add(i, Math.pow(2.0, i.toDouble()).toInt())
    }

    for (i in 0 until 10) {
        println(dynamicArray.get(i))
    }
    println("=========================")


    dynamicArray.remove(1)

    for (i in 0 until 9) {
        println(dynamicArray.get(i))
    }
    println("=========================")



}