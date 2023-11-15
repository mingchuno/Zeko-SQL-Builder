package io.zeko.db.sql.operators

import io.zeko.db.sql.QueryBlock

public fun eq(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "=", if (useRawValue) value else "?")
}

public fun neq(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "!=", if (useRawValue) value else "?")
}

public fun eq(field: String, value: Int): QueryBlock {
    return QueryBlock(field, "=", value.toString())
}

public fun neq(field: String, value: Int): QueryBlock {
    return QueryBlock(field, "!=", value.toString())
}

public fun eq(field: String, value: Double): QueryBlock {
    return QueryBlock(field, "=", value.toString())
}

public fun neq(field: String, value: Double): QueryBlock {
    return QueryBlock(field, "!=", value.toString())
}

public fun eq(field: String, value: Long): QueryBlock {
    return QueryBlock(field, "=", value.toString())
}

public fun neq(field: String, value: Long): QueryBlock {
    return QueryBlock(field, "!=", value.toString())
}

public fun eq(field: String, value: Any): QueryBlock {
    return QueryBlock(field, "=", "?")
}

public fun neq(field: String, value: Any): QueryBlock {
    return QueryBlock(field, "!=", "?")
}

public fun greater(field: String, field2: String): QueryBlock {
    return QueryBlock(field, ">", field2)
}

public fun greaterEq(field: String, field2: String): QueryBlock {
    return QueryBlock(field, ">=", field2)
}

public fun less(field: String, field2: String): QueryBlock {
    return QueryBlock(field, "<", field2)
}

public fun lessEq(field: String, field2: String): QueryBlock {
    return QueryBlock(field, "<=", field2)
}

public fun greater(field: String, value: Int): QueryBlock {
    return QueryBlock(field, ">", value.toString())
}

public fun greaterEq(field: String, value: Int): QueryBlock {
    return QueryBlock(field, ">=", value.toString())
}

public fun less(field: String, value: Int): QueryBlock {
    return QueryBlock(field, "<", value.toString())
}

public fun lessEq(field: String, value: Int): QueryBlock {
    return QueryBlock(field, "<=", value.toString())
}

public fun greater(field: String, value: Long): QueryBlock {
    return QueryBlock(field, ">", value.toString())
}

public fun greaterEq(field: String, value: Long): QueryBlock {
    return QueryBlock(field, ">=", value.toString())
}

public fun less(field: String, value: Long): QueryBlock {
    return QueryBlock(field, "<", value.toString())
}

public fun lessEq(field: String, value: Long): QueryBlock {
    return QueryBlock(field, "<=", value.toString())
}

public fun greater(field: String, value: Double): QueryBlock {
    return QueryBlock(field, ">", value.toString())
}

public fun greaterEq(field: String, value: Double): QueryBlock {
    return QueryBlock(field, ">=", value.toString())
}

public fun less(field: String, value: Double): QueryBlock {
    return QueryBlock(field, "<", value.toString())
}

public fun lessEq(field: String, value: Double): QueryBlock {
    return QueryBlock(field, "<=", value.toString())
}

public fun greater(field: String, value: Any): QueryBlock {
    return QueryBlock(field, ">", "?")
}

public fun greaterEq(field: String, value: Any): QueryBlock {
    return QueryBlock(field, ">=", "?")
}

public fun less(field: String, value: Any): QueryBlock {
    return QueryBlock(field, "<", "?")
}

public fun lessEq(field: String, value: Any): QueryBlock {
    return QueryBlock(field, "<=", "?")
}

public fun like(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "LIKE", if (useRawValue) "'${value.replace("'", "''")}'" else "?")
}

public fun notLike(field: String, value: String, useRawValue: Boolean = false): QueryBlock {
    return QueryBlock(field, "NOT LIKE", if (useRawValue) "'${value.replace("'", "''")}'" else "?")
}

public fun regexp(field: String, regex: String): QueryBlock {
    return QueryBlock("REGEX_LIKE( ", field, ", '$regex' )")
}

public fun regexp(field: String, regex: String, regexOption: String): QueryBlock {
    return QueryBlock("REGEX_LIKE( ", field, ", '$regex', '$regexOption' )")
}

public fun notRegexp(field: String, regex: String): QueryBlock {
    return QueryBlock("NOT REGEX_LIKE( ", field, ", '$regex' )")
}

public fun notRegexp(field: String, regex: String, regexOption: String): QueryBlock {
    return QueryBlock("NOT REGEX_LIKE( ", field, ", '$regex', '$regexOption' )")
}

public fun match(field: String, search: String, useRawValue: Boolean): QueryBlock {
    val value = if (useRawValue) "'${search.replace("'", "''")}'" else "?"
    return QueryBlock("MATCH( ", field, ") AGAINST ( $value IN NATURAL LANGUAGE MODE )")
}

public fun match(field: List<String>, search: String, useRawValue: Boolean): QueryBlock {
    val value = if (useRawValue) "'${search.replace("'", "''")}'" else "?"
    return QueryBlock(
        "MATCH( ",
        field.joinToString(","),
        ") AGAINST ( $value IN NATURAL LANGUAGE MODE )"
    )
}

public fun match(
    field: String,
    search: String,
    mode: String = "NATURAL LANGUAGE MODE"
): QueryBlock {
    return QueryBlock("MATCH( ", field, ") AGAINST ( ? IN $mode )")
}

public fun match(
    field: List<String>,
    search: String,
    mode: String = "NATURAL LANGUAGE MODE"
): QueryBlock {
    return QueryBlock("MATCH( ", field.joinToString(","), ") AGAINST ( ? IN $mode )")
}

public fun between(
    field: String,
    value1: String,
    value2: String,
    useRawValue: Boolean = false
): QueryBlock {
    if (!useRawValue) {
        return QueryBlock("$field BETWEEN ", "?", " AND ?")
    }
    return QueryBlock("$field BETWEEN ", value1, " AND $value2")
}

public fun between(field: String, value1: Int, value2: Int): QueryBlock {
    return QueryBlock("$field BETWEEN ", "$value1", " AND $value2")
}

public fun between(field: String, value1: Long, value2: Long): QueryBlock {
    return QueryBlock("$field BETWEEN ", "$value1", " AND $value2")
}

public fun between(field: String, value1: Double, value2: Double): QueryBlock {
    return QueryBlock("$field BETWEEN ", "$value1", " AND $value2")
}

public fun isNotNull(stmt: String): QueryBlock {
    return QueryBlock(stmt, "IS NOT NULL")
}

public fun isNull(stmt: String): QueryBlock {
    return QueryBlock(stmt, "IS NULL")
}

public fun inList(stmt: String, values: String): QueryBlock {
    return QueryBlock(stmt, "IN", "($values)")
}

public fun inList(stmt: String, valuesSize: Int): QueryBlock {
    var valEsp = "?,".repeat(valuesSize)
    valEsp = valEsp.substring(0, valEsp.length - 1)
    return QueryBlock(stmt, "IN", "($valEsp)")
}

public fun inList(stmt: String, values: List<Any>, useRawValue: Boolean = false): QueryBlock {
    var valEsp = ""
    if (useRawValue) {
        values.forEach {
            if (it is String) {
                valEsp += "'${it.replace("'", "''")}',"
            } else {
                valEsp += "$it,"
            }
        }
    } else {
        valEsp = "?,".repeat(values.size)
    }
    valEsp = valEsp.substring(0, valEsp.length - 1)
    return QueryBlock(stmt, "IN", "($valEsp)")
}

public fun inList(stmt: String, values: Array<*>): QueryBlock {
    var valEsp = ""
    values.forEach {
        if (it is String) {
            valEsp += "'${it.replace("'", "''")}',"
        } else {
            valEsp += "$it,"
        }
    }
    valEsp = valEsp.substring(0, valEsp.length - 1)
    return QueryBlock(stmt, "IN", "($valEsp)")
}

public fun notInList(stmt: String, values: String): QueryBlock {
    return QueryBlock(stmt, "NOT IN", "($values)")
}

public fun notInList(stmt: String, valuesSize: Int): QueryBlock {
    var valEsp = "?,".repeat(valuesSize)
    valEsp = valEsp.substring(0, valEsp.length - 1)
    return QueryBlock(stmt, "NOT IN", "($valEsp)")
}

public fun notInList(stmt: String, values: List<Any>, useRawValue: Boolean = false): QueryBlock {
    var valEsp = ""
    if (useRawValue) {
        values.forEach {
            if (it is String) {
                valEsp += "'${it.replace("'", "''")}',"
            } else {
                valEsp += "$it,"
            }
        }
    } else {
        valEsp = "?,".repeat(values.size)
    }
    valEsp = valEsp.substring(0, valEsp.length - 1)
    return QueryBlock(stmt, "NOT IN", "($valEsp)")
}

public fun notInList(stmt: String, values: Array<*>): QueryBlock {
    var valEsp = ""
    values.forEach {
        if (it is String) {
            valEsp += "'${it.replace("'", "''")}',"
        } else {
            valEsp += "$it,"
        }
    }
    valEsp = valEsp.substring(0, valEsp.length - 1)
    return QueryBlock(stmt, "NOT IN", "($valEsp)")
}

public fun sub(value: QueryBlock): QueryBlock {
    return QueryBlock("( ", value.toString(), " )")
}

public fun sub(value: String): QueryBlock {
    return QueryBlock("( ", value, " )")
}
