package io.zeko.db.sql.aggregations

import io.zeko.db.sql.QueryBlock

public fun sum(field: String): QueryBlock {
    return QueryBlock("SUM( ", field, " ) ")
}

public fun count(field: String): QueryBlock {
    return QueryBlock("COUNT( ", field, " ) ")
}

public fun avg(field: String): QueryBlock {
    return QueryBlock("AVG( ", field, " ) ")
}

public fun min(field: String): QueryBlock {
    return QueryBlock("MIN( ", field, " ) ")
}

public fun max(field: String): QueryBlock {
    return QueryBlock("MAX( ", field, " ) ")
}

public fun sum(field: String, value: Int): QueryBlock {
    return sumEq(field, value)
}

public fun sumEq(field: String, value: Int): QueryBlock {
    return agg("SUM", field, "=", value)
}

public fun sumGt(field: String, value: Int): QueryBlock {
    return agg("SUM", field, ">", value)
}

public fun sumLt(field: String, value: Int): QueryBlock {
    return agg("SUM", field, "<", value)
}

public fun sumGe(field: String, value: Int): QueryBlock {
    return agg("SUM", field, ">=", value)
}

public fun sumLe(field: String, value: Int): QueryBlock {
    return agg("SUM", field, "<=", value)
}

public fun count(field: String, value: Int): QueryBlock {
    return countEq(field, value)
}

public fun countEq(field: String, value: Int): QueryBlock {
    return agg("COUNT", field, "=", value)
}

public fun countGt(field: String, value: Int): QueryBlock {
    return agg("COUNT", field, ">", value)
}

public fun countLt(field: String, value: Int): QueryBlock {
    return agg("COUNT", field, "<", value)
}

public fun countGe(field: String, value: Int): QueryBlock {
    return agg("COUNT", field, ">=", value)
}

public fun countLe(field: String, value: Int): QueryBlock {
    return agg("COUNT", field, "<=", value)
}

public fun avg(field: String, value: Int): QueryBlock {
    return avgEq(field, value)
}

public fun avgEq(field: String, value: Int): QueryBlock {
    return agg("AVG", field, "=", value)
}

public fun avgGt(field: String, value: Int): QueryBlock {
    return agg("AVG", field, ">", value)
}

public fun avgLt(field: String, value: Int): QueryBlock {
    return agg("AVG", field, "<", value)
}

public fun avgGe(field: String, value: Int): QueryBlock {
    return agg("AVG", field, ">=", value)
}

public fun avgLe(field: String, value: Int): QueryBlock {
    return agg("AVG", field, "<=", value)
}

public fun min(field: String, value: Int): QueryBlock {
    return minEq(field, value)
}

public fun minEq(field: String, value: Int): QueryBlock {
    return agg("MIN", field, "=", value)
}

public fun minGt(field: String, value: Int): QueryBlock {
    return agg("MIN", field, ">", value)
}

public fun minLt(field: String, value: Int): QueryBlock {
    return agg("MIN", field, "<", value)
}

public fun minGe(field: String, value: Int): QueryBlock {
    return agg("MIN", field, ">=", value)
}

public fun minLe(field: String, value: Int): QueryBlock {
    return agg("MIN", field, "<=", value)
}

public fun max(field: String, value: Int): QueryBlock {
    return maxEq(field, value)
}

public fun maxEq(field: String, value: Int): QueryBlock {
    return agg("MAX", field, "=", value)
}

public fun maxGt(field: String, value: Int): QueryBlock {
    return agg("MAX", field, ">", value)
}

public fun maxLt(field: String, value: Int): QueryBlock {
    return agg("MAX", field, "<", value)
}

public fun maxGe(field: String, value: Int): QueryBlock {
    return agg("MAX", field, ">=", value)
}

public fun maxLe(field: String, value: Int): QueryBlock {
    return agg("MAX", field, "<=", value)
}

public fun agg(funcName: String, field: String, operator: String, value: Int): QueryBlock {
    return QueryBlock("$funcName(", field, ") $operator $value")
}

public fun sumEq(field: String, value: Long): QueryBlock {
    return agg("SUM", field, "=", value)
}

public fun sumGt(field: String, value: Long): QueryBlock {
    return agg("SUM", field, ">", value)
}

public fun sumLt(field: String, value: Long): QueryBlock {
    return agg("SUM", field, "<", value)
}

public fun sumGe(field: String, value: Long): QueryBlock {
    return agg("SUM", field, ">=", value)
}

public fun sumLe(field: String, value: Long): QueryBlock {
    return agg("SUM", field, "<=", value)
}

public fun count(field: String, value: Long): QueryBlock {
    return countEq(field, value)
}

public fun countEq(field: String, value: Long): QueryBlock {
    return agg("COUNT", field, "=", value)
}

public fun countGt(field: String, value: Long): QueryBlock {
    return agg("COUNT", field, ">", value)
}

public fun countLt(field: String, value: Long): QueryBlock {
    return agg("COUNT", field, "<", value)
}

public fun countGe(field: String, value: Long): QueryBlock {
    return agg("COUNT", field, ">=", value)
}

public fun countLe(field: String, value: Long): QueryBlock {
    return agg("COUNT", field, "<=", value)
}

public fun avg(field: String, value: Long): QueryBlock {
    return avgEq(field, value)
}

public fun avgEq(field: String, value: Long): QueryBlock {
    return agg("AVG", field, "=", value)
}

public fun avgGt(field: String, value: Long): QueryBlock {
    return agg("AVG", field, ">", value)
}

public fun avgLt(field: String, value: Long): QueryBlock {
    return agg("AVG", field, "<", value)
}

public fun avgGe(field: String, value: Long): QueryBlock {
    return agg("AVG", field, ">=", value)
}

public fun avgLe(field: String, value: Long): QueryBlock {
    return agg("AVG", field, "<=", value)
}

public fun min(field: String, value: Long): QueryBlock {
    return minEq(field, value)
}

public fun minEq(field: String, value: Long): QueryBlock {
    return agg("MIN", field, "=", value)
}

public fun minGt(field: String, value: Long): QueryBlock {
    return agg("MIN", field, ">", value)
}

public fun minLt(field: String, value: Long): QueryBlock {
    return agg("MIN", field, "<", value)
}

public fun minGe(field: String, value: Long): QueryBlock {
    return agg("MIN", field, ">=", value)
}

public fun minLe(field: String, value: Long): QueryBlock {
    return agg("MIN", field, "<=", value)
}

public fun max(field: String, value: Long): QueryBlock {
    return maxEq(field, value)
}

public fun maxEq(field: String, value: Long): QueryBlock {
    return agg("MAX", field, "=", value)
}

public fun maxGt(field: String, value: Long): QueryBlock {
    return agg("MAX", field, ">", value)
}

public fun maxLt(field: String, value: Long): QueryBlock {
    return agg("MAX", field, "<", value)
}

public fun maxGe(field: String, value: Long): QueryBlock {
    return agg("MAX", field, ">=", value)
}

public fun maxLe(field: String, value: Long): QueryBlock {
    return agg("MAX", field, "<=", value)
}

public fun agg(funcName: String, field: String, operator: String, value: Long): QueryBlock {
    return QueryBlock("$funcName(", field, ") $operator $value")
}

public fun sumEq(field: String, value: Double): QueryBlock {
    return agg("SUM", field, "=", value)
}

public fun sumGt(field: String, value: Double): QueryBlock {
    return agg("SUM", field, ">", value)
}

public fun sumLt(field: String, value: Double): QueryBlock {
    return agg("SUM", field, "<", value)
}

public fun sumGe(field: String, value: Double): QueryBlock {
    return agg("SUM", field, ">=", value)
}

public fun sumLe(field: String, value: Double): QueryBlock {
    return agg("SUM", field, "<=", value)
}

public fun count(field: String, value: Double): QueryBlock {
    return countEq(field, value)
}

public fun countEq(field: String, value: Double): QueryBlock {
    return agg("COUNT", field, "=", value)
}

public fun countGt(field: String, value: Double): QueryBlock {
    return agg("COUNT", field, ">", value)
}

public fun countLt(field: String, value: Double): QueryBlock {
    return agg("COUNT", field, "<", value)
}

public fun countGe(field: String, value: Double): QueryBlock {
    return agg("COUNT", field, ">=", value)
}

public fun countLe(field: String, value: Double): QueryBlock {
    return agg("COUNT", field, "<=", value)
}

public fun avg(field: String, value: Double): QueryBlock {
    return avgEq(field, value)
}

public fun avgEq(field: String, value: Double): QueryBlock {
    return agg("AVG", field, "=", value)
}

public fun avgGt(field: String, value: Double): QueryBlock {
    return agg("AVG", field, ">", value)
}

public fun avgLt(field: String, value: Double): QueryBlock {
    return agg("AVG", field, "<", value)
}

public fun avgGe(field: String, value: Double): QueryBlock {
    return agg("AVG", field, ">=", value)
}

public fun avgLe(field: String, value: Double): QueryBlock {
    return agg("AVG", field, "<=", value)
}

public fun min(field: String, value: Double): QueryBlock {
    return minEq(field, value)
}

public fun minEq(field: String, value: Double): QueryBlock {
    return agg("MIN", field, "=", value)
}

public fun minGt(field: String, value: Double): QueryBlock {
    return agg("MIN", field, ">", value)
}

public fun minLt(field: String, value: Double): QueryBlock {
    return agg("MIN", field, "<", value)
}

public fun minGe(field: String, value: Double): QueryBlock {
    return agg("MIN", field, ">=", value)
}

public fun minLe(field: String, value: Double): QueryBlock {
    return agg("MIN", field, "<=", value)
}

public fun max(field: String, value: Double): QueryBlock {
    return maxEq(field, value)
}

public fun maxEq(field: String, value: Double): QueryBlock {
    return agg("MAX", field, "=", value)
}

public fun maxGt(field: String, value: Double): QueryBlock {
    return agg("MAX", field, ">", value)
}

public fun maxLt(field: String, value: Double): QueryBlock {
    return agg("MAX", field, "<", value)
}

public fun maxGe(field: String, value: Double): QueryBlock {
    return agg("MAX", field, ">=", value)
}

public fun maxLe(field: String, value: Double): QueryBlock {
    return agg("MAX", field, "<=", value)
}

public fun agg(funcName: String, field: String, operator: String, value: Double): QueryBlock {
    return QueryBlock("$funcName(", field, ") $operator $value")
}

public fun agg(funcName: String, field: String, operator: String, value: String): QueryBlock {
    return QueryBlock("$funcName(", field, ") $operator $value")
}
