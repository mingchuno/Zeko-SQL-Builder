package io.zeko.db.sql

import io.zeko.db.sql.utilities.toSnakeCase
import io.zeko.model.Entity

public abstract class DataManipulation {
    protected lateinit var entity: Entity
    protected var parameterize: Boolean = false
    protected var espTableName: Boolean = false

    public open fun escapeTable(espTableName: Boolean): DataManipulation {
        this.espTableName = espTableName
        return this
    }

    public fun isTableNameEscaped(): Boolean {
        return this.espTableName
    }

    public fun getTableName(): String {
        var table =
            (if (entity != null && entity.tableName().isNotBlank()) entity.tableName()
            else "" + entity::class.simpleName?.toSnakeCase())
        if (this.espTableName) table = "\"$table\""
        return table
    }

    public fun params(): List<Any> {
        val values = arrayListOf<Any>()
        val entries = entity.dataMap().entries
        for ((_, value) in entries) {
            if (value != null) {
                values.add(value)
            }
        }
        return values
    }

    public fun toMap(): MutableMap<String, Any?> {
        return entity.dataMap()
    }

    public open fun shouldIgnoreType(value: Any?): Boolean {
        return when (value) {
            is List<*> -> true
            is Array<*> -> true
            is Map<*, *> -> true
            is Set<*> -> true
            is Entity -> true
            else -> false
        }
    }

    public open fun toSql(): String = ""
}
