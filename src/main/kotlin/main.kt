import classes.BaseTransformation
import classes.Helper.Companion.printTime
import classes.Matrix

fun main(args: Array<String>) {
    val table = Matrix(5,
        1, 0, 2, 1, 1,
        2, -1, 5, 3, 4,
        2, 1, 3, 1, 0,
        -1, -1, -1, 0, 1,
    )
    BaseTransformation.start(table)
}