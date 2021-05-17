package math

class Matrix() {
    private var elements = arrayOf(doubleArrayOf())

    //#region Constructors
    constructor(elements: Array<DoubleArray>): this() {
        this.elements = elements
    }

    constructor(elements: List<List<Number>>): this() {
        this.elements = elements.map { it.map { num -> num.toDouble() }.toDoubleArray() }.toTypedArray()
    }

    constructor(h: Int, w: Int): this() {
        elements = Array(h) { DoubleArray(w) { 0.0 } }
    }

    constructor(h: Int, w: Int, unit: Double): this(h, w) {
        val smallerDimension = if (h < w) h else w
        for (i in 1..smallerDimension) {
            set(i, i, unit)
        }
    }

    constructor(h: Int, w: Int, f: (y: Int, x: Int) -> Double): this(h, w) {
        for (y in 1..getHeight()) {
            for (x in 1..getWidth()) {
                this[y, x] = f(y, x)
            }
        }
    }

    constructor(width: Int, vararg values: Double): this() {
        val rows = mutableListOf<MutableList<Double>>()
        for (i in values.indices) {
            if (i % width == 0) rows.add(mutableListOf())
            rows.last().add(values[i])
        }
        elements = rows.map { it.map { num -> num }.toDoubleArray() }.toTypedArray()
    }

    constructor(width: Int, vararg values: Int): this() {
        val rows = mutableListOf<MutableList<Double>>()
        for (i in values.indices) {
            if (i % width == 0) rows.add(mutableListOf())
            rows.last().add(values[i].toDouble())
        }
        elements = rows.map { it.map { num -> num }.toDoubleArray() }.toTypedArray()
    }
    //#endregion

    fun getWidth(): Int = elements.firstOrNull()?.size ?: 0
    fun getHeight(): Int = elements.size
    fun getValuesList(): List<Double> {
        val list = mutableListOf<Double>()
        elements.map { list.addAll(it.toList()) }
        return list
    }
    fun getRow(row: Int): List<Double> {
        return elements[row - 1].toList()
    }
    fun getCol(col: Int): List<Double> {
        val list = mutableListOf<Double>()
        list.addAll(elements.map { it[col - 1] })
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

    fun subMatrixByRow(row: Int): Matrix {
        val sub = Matrix(getHeight() - 1, getWidth() - 1)
        for (x in 1 until getWidth()) {
            for (y in 1..getHeight()) {
                if (y > row) sub[y - 1, x] = get(y, x + 1)
                if (y < row) sub[y, x] = get(y, x + 1)
            }
        }
        return sub
    }

    fun toLineString(): String {
        return elements.joinToString(" | ") { it.joinToString() }
    }

    override fun toString(): String {
        return elements.joinToString("\n") { it.joinToString() }
    }

    companion object {
        fun det(m: Matrix): Double {
            if (!m.isSquare()) throw MatrixWrongDimensionException(m)
            // 2x2 size
            if (m.getWidth() == 2) {
                val value = m[1, 1] * m[2, 2] - m[1, 2] * m[2, 1]
                println("det(${m[1,1]}, ${m[1,2]} | ${m[2,1]}, ${m[2,2]}) = $value")
                return value
            }
            // Larger size
            var sum = 0.0
            for (i in 1..m.getHeight()) {
                var subDet = det(m.subMatrixByRow(i))
                if (i % 2 == 0) subDet *= -1
                sum += (m[i, 1] * subDet)
                println("det részeredmény: ${m[i, 1]} * $subDet = ${m[i, 1] * subDet}\nÖsszeg eddig: $sum")
            }
            println("det(${m.toLineString()}) = $sum")
            return sum
        }
    }
}