package db

class Sql(var value: String = "") {
    companion object {
        fun sAs(alias: String): String = "AS [$alias]"
        fun sDistinct(): String = "DISTINCT"
        fun sTop(count: Int): String = "TOP($count)"
    }

    fun select(vararg fields: String = arrayOf("*")): Sql {
        value += "SELECT ${fields.joinToString()} "
        return this
    }

    fun from(table: String): Sql {
        value += "FROM $table "
        return this
    }

    fun where(condition: String): Sql {
        value += "WHERE $condition "
        return this
    }

    fun orderBy(fieldName: String, descending: Boolean = false): Sql {
        value += "ORDER BY $fieldName ${if (descending) "DESC" else "ASC"} "
        return this
    }

    fun orderByAlso(fieldName: String, descending: Boolean = false): Sql {
        value += ", $fieldName ${if (descending) "DESC" else "ASC"} "
        return this
    }

    fun groupBy(fieldName: String): Sql {
        value += "GROUP BY $fieldName "
        return this
    }

    fun build(): String {
        return "$value;"
    }

    override fun toString(): String {
        return value
    }
}