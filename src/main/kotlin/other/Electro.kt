package other

class Electro {
    companion object {
        infix fun Int.replus(other: Int): Double {
            return (this * other) / (this + other).toDouble()
        }

        infix fun Double.replus(other: Double): Double {
            return (this * other) / (this + other)
        }

        infix fun Int.replus(other: Double): Double {
            return (this * other) / (this + other)
        }

        infix fun Double.replus(other: Int): Double {
            return (this * other) / (this + other)
        }
    }
}