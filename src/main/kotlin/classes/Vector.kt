package classes

class Vector() {
    private var items = mutableListOf<Double>()

    constructor(vararg values: Int): this() {
        values.map { i -> items.add(i.toDouble()) }
    }

    constructor(vararg values: Float): this() {
        values.map { f -> items.add(f.toDouble()) }
    }

    constructor(vararg values: Double): this() {
        values.map { d -> items.add(d) }
    }

    constructor(value: Double, count: Int): this() {
        repeat(count) { items.add(value) }
    }

    //region Operators

    operator fun get(index: Int): Double = if (index < items.size) items[index] else 0.0

    operator fun set(index: Int, value: Double) {
        while (index >= items.size) items.add(0.0)
        items[index] = value
    }

    operator fun plus(other: Vector): Vector {
        val ans = Vector()
        for (i in 0..items.size) {
            ans[i] = this[i] + other[i]
        }
        return ans
    }

    operator fun plusAssign(other: Vector) {
        for (i in 0..items.size) {
            this[i] += other[i]
        }
    }

    operator fun minus(other: Vector): Vector {
        val ans = Vector()
        for (i in 0..items.size) {
            ans[i] = this[i] - other[i]
        }
        return ans
    }

    operator fun minusAssign(other: Vector) {
        for (i in 0..items.size) {
            this[i] -= other[i]
        }
    }

    operator fun times(other: Int): Vector {
        val ans = Vector()
        for (i in 0..items.size) {
            ans[i] = this[i] * other.toFloat()
        }
        return ans
    }

    operator fun times(other: Float): Vector {
        val ans = Vector()
        for (i in 0..items.size) {
            ans[i] = this[i] * other
        }
        return ans
    }

    operator fun timesAssign(other: Int) {
        items = items.map { it * other }.toMutableList()
    }

    operator fun timesAssign(other: Float) {
        items = items.map { it * other }.toMutableList()
    }

    //endregion

    fun isNullVector(): Boolean = !items.any { it != 0.0 }

    fun isUnitVector(): Boolean = items.contains(1f) && items.count { it == 0.0 } == items.size - 1

    fun getDimension(): Int = items.size

    fun setDimension(value: Int) {
        if (value >= items.size + 1) while (value >= items.size + 1) items.add(0.0)
        else while (value < items.size) items.remove(items.last())
    }

    fun toDiagonalMatrix(): Matrix {
        val m = Matrix(items.size, items.size)
        for (i in 0 until items.size) {
            m[i + 1, i + 1] = items[i]
        }
        return m
    }

    override fun equals(other: Any?): Boolean {
        return this.toString() == other.toString()
    }

    override fun toString(): String {
        return items.toString()
    }

    override fun hashCode(): Int {
        return items.hashCode()
    }
}