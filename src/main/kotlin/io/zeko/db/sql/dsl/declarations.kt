package io.zeko.db.sql.dsl

import io.zeko.db.sql.QueryBlock

public infix fun String.eq(value: String): QueryBlock {
    return io.zeko.db.sql.operators.eq(this, value)
}

public infix fun String.eq(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.eq(this, value)
}

public infix fun String.eq(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.eq(this, value)
}

public infix fun String.eq(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.eq(this, value)
}

public infix fun String.eq(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.eq(this, value)
}

public infix fun String.neq(value: String): QueryBlock {
    return io.zeko.db.sql.operators.neq(this, value)
}

public infix fun String.neq(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.neq(this, value)
}

public infix fun String.neq(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.neq(this, value)
}

public infix fun String.neq(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.neq(this, value)
}

public infix fun String.neq(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.neq(this, value)
}

public infix fun String.greater(value: String): QueryBlock {
    return io.zeko.db.sql.operators.greater(this, value)
}

public infix fun String.greaterEq(value: String): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this, value)
}

public infix fun String.less(value: String): QueryBlock {
    return io.zeko.db.sql.operators.less(this, value)
}

public infix fun String.lessEq(value: String): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this, value)
}

public infix fun String.greater(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.greater(this, value)
}

public infix fun String.greaterEq(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this, value)
}

public infix fun String.less(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.less(this, value)
}

public infix fun String.lessEq(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this, value)
}

public infix fun String.greater(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.greater(this, value)
}

public infix fun String.greaterEq(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this, value)
}

public infix fun String.less(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.less(this, value)
}

public infix fun String.lessEq(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this, value)
}

public infix fun String.greater(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.greater(this, value)
}

public infix fun String.greaterEq(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this, value)
}

public infix fun String.less(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.less(this, value)
}

public infix fun String.lessEq(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this, value)
}

public infix fun String.greater(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.greater(this, value)
}

public infix fun String.greaterEq(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this, value)
}

public infix fun String.less(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.less(this, value)
}

public infix fun String.lessEq(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this, value)
}

public infix fun QueryBlock.eq(value: String): QueryBlock {
    return io.zeko.db.sql.operators.eq(this.toString(), value)
}

public infix fun QueryBlock.eq(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.eq(this.toString(), value)
}

public infix fun QueryBlock.eq(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.eq(this.toString(), value)
}

public infix fun QueryBlock.eq(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.eq(this.toString(), value)
}

public infix fun QueryBlock.eq(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.eq(this.toString(), value)
}

public infix fun QueryBlock.neq(value: String): QueryBlock {
    return io.zeko.db.sql.operators.neq(this.toString(), value)
}

public infix fun QueryBlock.neq(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.neq(this.toString(), value)
}

public infix fun QueryBlock.neq(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.neq(this.toString(), value)
}

public infix fun QueryBlock.neq(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.neq(this.toString(), value)
}

public infix fun QueryBlock.neq(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.neq(this.toString(), value)
}

public infix fun QueryBlock.greater(value: String): QueryBlock {
    return io.zeko.db.sql.operators.greater(this.toString(), value)
}

public infix fun QueryBlock.greaterEq(value: String): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this.toString(), value)
}

public infix fun QueryBlock.less(value: String): QueryBlock {
    return io.zeko.db.sql.operators.less(this.toString(), value)
}

public infix fun QueryBlock.lessEq(value: String): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this.toString(), value)
}

public infix fun QueryBlock.greater(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.greater(this.toString(), value)
}

public infix fun QueryBlock.greaterEq(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this.toString(), value)
}

public infix fun QueryBlock.less(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.less(this.toString(), value)
}

public infix fun QueryBlock.lessEq(value: Int): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this.toString(), value)
}

public infix fun QueryBlock.greater(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.greater(this.toString(), value)
}

public infix fun QueryBlock.greaterEq(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this.toString(), value)
}

public infix fun QueryBlock.less(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.less(this.toString(), value)
}

public infix fun QueryBlock.lessEq(value: Long): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this.toString(), value)
}

public infix fun QueryBlock.greater(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.greater(this.toString(), value)
}

public infix fun QueryBlock.greaterEq(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this.toString(), value)
}

public infix fun QueryBlock.less(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.less(this.toString(), value)
}

public infix fun QueryBlock.lessEq(value: Double): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this.toString(), value)
}

public infix fun QueryBlock.greater(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.greater(this.toString(), value)
}

public infix fun QueryBlock.greaterEq(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.greaterEq(this.toString(), value)
}

public infix fun QueryBlock.less(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.less(this.toString(), value)
}

public infix fun QueryBlock.lessEq(value: Any): QueryBlock {
    return io.zeko.db.sql.operators.lessEq(this.toString(), value)
}

public infix fun String.like(value: String): QueryBlock {
    return io.zeko.db.sql.operators.like(this, value)
}

public infix fun String.notLike(value: String): QueryBlock {
    return io.zeko.db.sql.operators.notLike(this, value)
}

public infix fun String.regexp(value: String): QueryBlock {
    return io.zeko.db.sql.operators.regexp(this, value)
}

public infix fun String.notRegexp(value: String): QueryBlock {
    return io.zeko.db.sql.operators.notRegexp(this, value)
}

public infix fun String.match(value: String): QueryBlock {
    return io.zeko.db.sql.operators.match(this, value)
}

public infix fun List<String>.match(value: String): QueryBlock {
    return io.zeko.db.sql.operators.match(this, value)
}

public infix fun String.isNotNull(value: Boolean): QueryBlock {
    if (value) {
        return io.zeko.db.sql.operators.isNotNull(this)
    }
    return QueryBlock("", "")
}

public infix fun String.isNull(value: Boolean): QueryBlock {
    if (value) {
        return io.zeko.db.sql.operators.isNull(this)
    }
    return QueryBlock("", "")
}

public infix fun String.inList(values: String): QueryBlock {
    return io.zeko.db.sql.operators.inList(this, values)
}

public infix fun String.inList(values: List<Any>): QueryBlock {
    return io.zeko.db.sql.operators.inList(this, values)
}

public infix fun String.inList(values: Array<*>): QueryBlock {
    return io.zeko.db.sql.operators.inList(this, values)
}

public infix fun String.inList(valueSize: Int): QueryBlock {
    return io.zeko.db.sql.operators.inList(this, valueSize)
}

public infix fun String.notInList(values: String): QueryBlock {
    return io.zeko.db.sql.operators.notInList(this, values)
}

public infix fun String.notInList(values: List<Any>): QueryBlock {
    return io.zeko.db.sql.operators.notInList(this, values)
}

public infix fun String.notInList(values: Array<*>): QueryBlock {
    return io.zeko.db.sql.operators.notInList(this, values)
}

public infix fun String.notInList(valueSize: Int): QueryBlock {
    return io.zeko.db.sql.operators.notInList(this, valueSize)
}

public infix fun QueryBlock.and(value: QueryBlock): QueryBlock {
    return QueryBlock(this.toString(), " AND ", value.toString())
}

public infix fun QueryBlock.or(value: QueryBlock): QueryBlock {
    return QueryBlock(this.toString(), " OR ", value.toString())
}

public infix fun String.between(values: Pair<*, *>): QueryBlock {
    val value1 = values.first
    if (value1 is String) {
        val value2 = values.second.toString()
        return io.zeko.db.sql.operators.between(this, value1, value2)
    } else if (value1 is Int) {
        val value2 = values.second as Int
        return io.zeko.db.sql.operators.between(this, value1, value2)
    } else if (value1 is Long) {
        val value2 = values.second as Long
        return io.zeko.db.sql.operators.between(this, value1, value2)
    } else if (value1 is Double) {
        val value2 = values.second as Double
        return io.zeko.db.sql.operators.between(this, value1, value2)
    } else {
        val value2 = values.second
        return io.zeko.db.sql.operators.between(this, value1.toString(), value2.toString())
    }
    return QueryBlock("", "")
}
