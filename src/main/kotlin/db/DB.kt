package db

import com.google.gson.GsonBuilder
import com.microsoft.sqlserver.jdbc.SQLServerException
import java.sql.*


class DB {
    companion object {
        var defaultDatabase = "h35_gwbtsu"

        private var connection: Connection? = null

        fun connect() {
            DriverManager.registerDriver(com.microsoft.sqlserver.jdbc.SQLServerDriver())
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
            val dbURL = "jdbc:sqlserver://adatb-mssql.mik.uni-pannon.hu:2019;user=h35_gwbtsu;password=DYE7Kaz?Xw"
            connection = DriverManager.getConnection(dbURL)
        }

        fun execute(sql: String): ResultSet? {
            try {
                val s = connection?.createStatement()
                return s?.executeQuery("USE $defaultDatabase; $sql")
            }
            catch (err: SQLServerException) {
                println("SQL: $sql\nERROR: ${err.message}")
                return null
            }
        }

        fun ResultSet?.toList(): List<Map<String, String>> {
            val list = mutableListOf<MutableMap<String, String>>()

            val rsmd: ResultSetMetaData? = this?.metaData
            val columnsNumber: Int = rsmd?.columnCount ?: 0
            while (this?.next() == true) {
                list.add(mutableMapOf())
                for (i in 1..columnsNumber) {
                    val columnValue: String = this.getString(i) ?: "null"
                    list.last()[rsmd?.getColumnName(i) ?: "null"] = columnValue
                }
            }

            return list
        }

        fun printResults(results: ResultSet?) {
            val list = results.toList()
            val json = GsonBuilder().setPrettyPrinting().create().toJson(list)
            println(json)
        }

        fun disconnect() {
            connection?.close()
        }
    }
}