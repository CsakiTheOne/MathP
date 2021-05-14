package classes

class Matrix() {
    private var elements = arrayOf(doubleArrayOf())

    //#region Constructors
    constructor(elements: Array<DoubleArray>): this() {
        this.elements = elements
    }

    constructor(elements: List<List<Double>>): this() {
        this.elements = elements.map { it.toDoubleArray() }.toTypedArray()
    }

    constructor(h: Int, w: Int): this() {
        elements = Array(h) { DoubleArray(w) { 0.0 } }
    }

    constructor(h: Int, w: Int, unit: Double): this(h, w) {
        val max = if (h < w) h else w
        for (i in 1..max) {
            this[i, i] = unit
        }
    }

    constructor(h: Int, w: Int, f: (y: Int, x: Int) -> Double): this(h, w) {
        for (y in 1..getHeight()) {
            for (x in 1..getWidth()) {
                this[y, x] = f(y, x)
            }
        }
    }
    //#endregion

    fun getWidth(): Int = elements.firstOrNull()?.size ?: 0
    fun getHeight(): Int = elements.size
    fun getValuesList(): List<Double> {
        val list = mutableListOf<Double>()
        elements.map { list.addAll(it.toList()) }
        return list
    }

    fun isSquare(): Boolean = getWidth() == getHeight()
    fun isDiagonal(): Boolean {
        if (!isSquare()) return false
        val newMatrix = Matrix(elements)
        val smallerDimension = if (getWidth() < getHeight()) getWidth() else getHeight()
        for (i in 1..smallerDimension) {
            if (newMatrix[i, i] == 0.0) {
                return false
            }
            newMatrix[i, i] = 0.0
        }
        return !newMatrix.getValuesList().any { it != 0.0 }
    }

    //#region Operators
    operator fun get(row: Int, col: Int): Double {
        return elements[row - 1][col - 1]
    }

    operator fun set(row: Int, col: Int, value: Double) {
        elements[row - 1][col - 1] = value
    }

    operator fun plus(other: Matrix): Matrix {
        if (getWidth() != other.getWidth() || getHeight() != other.getHeight()) throw MatrixWrongDimensionException(this, other)
        val newMatrix = Matrix(getHeight(), getWidth())
        for (y in 1..getHeight()) {
            for (x in 1..getWidth()) {
                newMatrix[y, x] = this[y, x] + other[y, x]
            }
        }
        return newMatrix
    }

    operator fun minus(other: Matrix): Matrix {
        if (getWidth() != other.getWidth() || getHeight() != other.getHeight()) throw MatrixWrongDimensionException(this, other)
        val newMatrix = Matrix(getHeight(), getWidth())
        for (y in 1..getHeight()) {
            for (x in 1..getWidth()) {
                newMatrix[y, x] = this[y, x] - other[y, x]
            }
        }
        return newMatrix
    }

    operator fun times(number: Double): Matrix {
        val newMatrix = Matrix(getHeight(), getWidth())
        for (y in 1..getHeight()) {
            for (x in 1..getWidth()) {
                newMatrix[y, x] = this[y, x] * number
            }
        }
        return newMatrix
    }

    operator fun div(number: Double): Matrix {
        val newMatrix = Matrix(getHeight(), getWidth())
        for (y in 1..getHeight()) {
            for (x in 1..getWidth()) {
                newMatrix[y, x] = this[y, x] / number
            }
        }
        return newMatrix
    }

    operator fun times(other: Matrix): Matrix {
        if (getWidth() != other.getHeight()) throw MatrixWrongDimensionException(this, other)
        val newMatrix = Matrix(getHeight(), other.getWidth())
        for (i in 1..getHeight()) {
            for (j in 1..other.getWidth()) {
                for (k in 1..getWidth()) {
                    newMatrix[i, j] += this[i, k] * other[k, j]
                }
            }
        }
        return newMatrix
    }
    //#endregion

    fun transposition(): Matrix {
        val newMatrix = Matrix(getWidth(), getHeight())
        for (y in 1..getHeight()) {
            for (x in 1..getWidth()) {
                newMatrix[x, y] = this[y, x]
            }
        }
        return newMatrix
    }

    fun subMatrix(row: Int): Matrix {
        val sub = Matrix(getHeight() - 1, getWidth() - 1)
        for (x in 1 until getWidth()) {
            for (y in 1..getHeight()) {
                if (y > row) sub[y - 1, x] = get(y, x + 1)
                if (y < row) sub[y, x] = get(y, x + 1)
            }
        }
        return sub
    }

    override fun toString(): String {
        return elements.joinToString("\n") { it.joinToString() }
    }

    companion object {
        fun det(m: Matrix): Double {
            if (!m.isSquare()) throw MatrixWrongDimensionException(m)
            // 2x2 size
            if (m.getWidth() == 2) {
                return m[1, 1] * m[2, 2] - m[1, 2] * m[2, 1]
            }
            // Larger size
            var sum = 0.0
            for (i in 1..m.getHeight()) {
                var subDet = det(m.subMatrix(i))
                if (i % 2 == 0) subDet *= -1
                println("det(matrix): $i. row: ${m[i, 1]} * $subDet = ${m[i, 1] * subDet}")
                sum += (m[i, 1] * subDet)
            }
            return sum
        }
    }
}