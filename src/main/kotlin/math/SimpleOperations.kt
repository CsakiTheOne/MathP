package math

import kotlin.math.floor

class SimpleOperations {
    companion object {

        // 1062 / 9
        infix fun Int.writtenDivide(other: Int): Int {

            println("$this maradékos osztása ezzel: $other")
            var ans = ""

            var startDigit = 0
            var endDigit = 1

            var currentNumber = 0
            while (endDigit <= this.toString().length) {
                currentNumber = "$currentNumber${this.toString().substring(startDigit, endDigit)}".toInt()
                var currentAns = floor(currentNumber / other.toDouble())

                if (currentNumber >= other) {
                    println("$currentNumber osztva $other számmal: ${floor(currentAns).toInt()} a maradék: ${currentNumber % other}")
                    ans += floor(currentAns).toInt()
                    currentNumber %= other
                    startDigit++
                    endDigit++
                }
                else {
                    println("$currentNumber osztva $other számmal nincs meg egyszer sem")
                    startDigit++
                    endDigit++
                }
            }

            println("A végeredmény: $ans")

            return ans.toInt()

        }

    }
}