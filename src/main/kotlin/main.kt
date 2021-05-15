import classes.BaseTransformation
import classes.Helper
import classes.Matrix

fun main(args: Array<String>) {
    val table = Matrix(listOf(
        listOf(1, 0, 2, 1, 1),
        listOf(2, -1, 5, 3, 4),
        listOf(2, 1, 3, 1, 0),
        listOf(-1, -1, -1, 0, 1),
    ))
    BaseTransformation.start(table)
}