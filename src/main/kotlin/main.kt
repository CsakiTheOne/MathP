import classes.Matrix

fun main(args: Array<String>) {
    val a = Matrix(listOf(
        listOf(1.0, 3.0, 0.0),
        listOf(-2.0, 2.0, 4.0),
        listOf(5.0, -5.0, 7.0),
    ))
    println("det(a) = ${Matrix.det(a)}")
}