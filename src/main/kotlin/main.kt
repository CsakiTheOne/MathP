import math.Matrix

fun main(args: Array<String>) {
    val m1 = Matrix(3,
    3, 1, 2,
    2, 3, 1)
    val m2 = Matrix(3, 2) { y, x -> (x + y).toDouble() }

    m1 * m2
}