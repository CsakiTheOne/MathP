package classes

import classes.Helper.Companion.printWithLength

class BaseTransformation {
    companion object {
        private fun printTable(table: Matrix) {
            for (y in -1..table.getHeight()) {
                when (y) {
                    -1 -> for (x in 1..table.getWidth()) printWithLength("a$x", 6)
                    0 -> for (x in 1..table.getWidth()) print("------")
                    else -> {
                        for (x in 1..table.getWidth()) printWithLength("${table[y, x]}", 6)
                    }
                }
                println()
            }
        }

        private fun step(table: Matrix, inBase: MutableMap<Int, Int>) {
            println("Tábla:")
            printTable(table)
            println("Bázisban lévő vektorok: ${inBase.map { "a${it.value} az e${it.key} helyén" }} Rang: ${inBase.size}")
            println("Válassz egy generáló elemet! Írd be a koordinátáit! Pl: 3,2 = 3. sor 2. oszlop")
            val coords = readLine() ?: "1,1"
            println(CharArray(60) { '_' })
            if (!coords.matches("[0-9],[0-9]".toRegex())) return
            val gy = coords.split(',')[0].toInt()
            val gx = coords.split(',')[1].toInt()
            val newTable = Matrix(table.getHeight(), table.getWidth())
            val deltas = mutableListOf<Double>()
            for (x in 1..table.getWidth()) {
                deltas.add(table[gy, x] / table[gy, gx])
            }
            println("Delták: $deltas")

            for (y in 1..table.getHeight()) {
                for (x in 1..table.getWidth()) {
                    newTable[y, x] = if (x == gx && y == gy) 1.0
                    else if (y == gy) table[y, x] / table[gy, gx]
                    else table[y, x] - (deltas[x - 1] * table[y, gx])
                }
            }

            inBase[gy] = gx
            step(newTable, inBase)
        }

        fun start(table: Matrix) {
            println("Bázis transzformálás kezdődött!")
            step(table, mutableMapOf())
        }
    }
}