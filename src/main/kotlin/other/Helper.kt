package other

class Helper {
    companion object {
        fun String.toCharPair(): List<Pair<Char, Char>> {
            val list = mutableListOf<Pair<Char, Char>>()
            var i = 0
            while (i < this.length) {
                list.add(this[i] to this[i + 1])
                i += 2
            }
            return list
        }

        fun printWithLength(text: String, length: Int) {
            for (i in 0 until length) {
                print (if (i < text.length) text[i] else " ")
            }
        }

        fun printTime(timerName: String = "Runtime", block: () -> Unit) {
            val start = System.currentTimeMillis()
            block()
            println("$timerName: ${System.currentTimeMillis() - start}ms")
        }
    }
}