import other.Electro.Companion.replus

fun main(args: Array<String>) {
    val temp = 30 replus 40 replus 50
    val a = 20.0 * ((40 replus 50 replus 30) / ((40 replus 50 replus 20) + 40)) * (2 / 3.0)
    val b = 10.0 * (30 replus 50 replus 30) / ((30 replus 50 replus 30) + 4) * (2 / 3.0)
    val c = -2 * 10 / ((30 replus 40 replus 50) + 30) * 20.0
    val sum = a + b + c

    println(temp)
    println("a b c")
    println(a)
    println(b)
    println(c)
    println(sum)
}
