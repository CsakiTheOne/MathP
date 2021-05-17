package classes

import classes.Helper.Companion.printWithLength

class BinaryWeight {
    companion object {
        private val twoPowers = listOf(1, 2, 4, 8, 16, 32, 64)

        fun start(listFirst: List<Int>, listLast: List<Int>) {
            val list = mutableListOf<Int>()
            list.addAll(listFirst)
            list.addAll(listLast)
            println("Bináris Súly: ${list.sorted()}")
            val col1 = mutableListOf<MutableList<Int>>()
            val col2 = mutableListOf<MutableList<String>>()
            val col3 = mutableListOf<MutableList<String>>()
            val col2NoTick = mutableListOf<String>()
            // Column 1
            list.sorted().map { n ->
                val onesCount = n.toString(2).count { it == '1' }
                while (col1.size <= onesCount) {
                    col1.add(mutableListOf())
                    col2.add(mutableListOf())
                    col3.add(mutableListOf())
                }
                col1[onesCount].add(n)
            }
            println("1. oszlop (hány 1-es van a szám bináris alakjában?)\n${col1.joinToString("\n")}")
            // Column 2
            for (categoryIndex in 0 until col1.size - 1) {
                for (num1 in col1[categoryIndex]) {
                    for (num2 in col1[categoryIndex + 1]) {
                        if (twoPowers.contains(num2 - num1)) {
                            col2[categoryIndex].add("$num1,$num2(${num2 - num1})")
                            col2NoTick.add("$num1,$num2(${num2 - num1})")
                        }
                    }
                }
            }
            println("2. oszlop (melyik számok különbsége adja 2 egyik hatványát ahol az első szám x+1. a második x. sorból van?)\n" +
                    col2.joinToString("\n") { "[" + it.joinToString("\t") + "]" })
            // Column 3 (a1,a2,b1,b2(x,y)): akkor lehet párosítani ha b2-b1=a2-a1 és b1-a1=b2-a2 és 2hatványok tartalmazza x-et és y-t
            for (categoryIndex in 0 until col1.size - 1) {
                for (pair1 in col2[categoryIndex]) {
                    for (pair2 in col2[categoryIndex + 1]) {
                        val a1 = pair1.split('(')[0].split(',')[0].toInt()
                        val a2 = pair1.split('(')[0].split(',')[1].toInt()
                        val b1 = pair2.split('(')[0].split(',')[0].toInt()
                        val b2 = pair2.split('(')[0].split(',')[1].toInt()
                        val x = a2 - a1
                        val y = b1 - a1
                        if (twoPowers.contains(x) && twoPowers.contains(y) && b2 - b1 == a2 - a1 && b1 - a1 == b2 - a2) {
                            col2NoTick.remove("$a1,$a2(${a2 - a1})")
                            col2NoTick.remove("$b1,$b2(${b2 - b1})")
                            if (a2 < b1) col3[categoryIndex].add("$a1,$a2,$b1,$b2($x,$y)")
                        }
                    }
                }
            }
            println("3. oszlop (csoda)\n${col3.joinToString("\n")}")
            println("2. oszlop nem pipált elemei: $col2NoTick")
            // Table
            println("Táblázat")
            printWithLength("", 20)
            for (n in listFirst.sorted()) {
                printWithLength(n.toString(), 3)
            }
            println()
            val tableRows = mutableListOf<String>()
            tableRows.addAll(col2NoTick)
            for (category in col3) tableRows.addAll(category)
            for (row in tableRows) {
                printWithLength(row, 20)
                val numbers = row.split('(')[0].split(',').map { it.toInt() }
                for (n in listFirst) {
                    printWithLength(if (numbers.contains(n)) "x" else "", 3)
                }
                println()
            }
        }
    }
}