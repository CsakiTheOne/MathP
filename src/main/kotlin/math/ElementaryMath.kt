package math

import kotlin.math.floor

class ElementaryMath {
    companion object {

        // 1062 / 9
        infix fun Int.writtenDivide(other: Int): Int {

            val ans = this / other
            println("$this : $other = $ans")

            var startDigit = 0
            var endDigit = 1

            var currentNumber = 0
            while (endDigit <= this.toString().length) {
                currentNumber = "$currentNumber${this.toString().substring(startDigit, endDigit)}".toInt()
                if (currentNumber >= other) {
                    if (startDigit > 1) println(" ".repeat(startDigit - 1) + currentNumber)
                    currentNumber %= other
                }
                startDigit++
                endDigit++
            }


            return ans

        }

    }
}