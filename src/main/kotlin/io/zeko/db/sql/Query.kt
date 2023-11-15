package io.zeko.db.sql

import java.util.*

public open class Query {
    internal var espChar: String

    internal var asChar: String

    internal var espTableName: Boolean

    private var currentTable: String = ""

    private val tableFrom = arrayListOf<Any>()

    private val fieldsToSelect by lazy { LinkedHashMap<String, Array<String>>() }

    private val tableToJoin by lazy { LinkedHashMap<String, ArrayList<Condition>>() }

    private val whereCondition by lazy { arrayListOf<Condition>() }

    private val groupBys by lazy { arrayListOf<String>() }

    private val havingCondition by lazy { arrayListOf<Condition>() }

    private val orderBy by lazy { arrayListOf<Sort>() }

    private val expression by lazy { EnumMap<CustomPart, List<QueryBlock>>(CustomPart::class.java) }

    private var limitOffset: Array<Int>? = null

    public constructor(espChar: String = "`", asChar: String = "=", espTableName: Boolean = false) {
        this.espChar = espChar
        this.asChar = asChar
        this.espTableName = espTableName
    }

    public fun table(name: String): Query {
        currentTable = name
        return this
    }

    public fun fields(vararg names: String): Query {
        fieldsToSelect[currentTable] = names as Array<String>
        return this
    }

    internal fun compileSelect(): SelectPart {
        val selectFields = mutableListOf<String>()
        val columns = mutableListOf<String>()

        for ((tbl, cols) in fieldsToSelect) {
            for (colName in cols) {
                if (colName.indexOf("=") != -1) {
                    val parts = colName.split(asChar)
                    val partField = parts[0].trim()
                    var tblLinkedCol: String
                    if (!espTableName) {
                        tblLinkedCol = partField
                    } else {
                        val fieldParts = partField.split(".")
                        val tblLinked = fieldParts[0]
                        tblLinkedCol = "${espChar}${tblLinked}${espChar}.${fieldParts[1]}"
                    }
                    val selfCol = parts[1].trim()
                    if (tbl == "") {
                        selectFields.add("$colName")
                    } else {
                        val aliasName = "$tbl-$selfCol"
                        columns.add(aliasName)
                        selectFields.add("$tblLinkedCol as $espChar$aliasName$espChar")
                    }
                } else {
                    if (tbl == "") {
                        selectFields.add("$colName")
                    } else {
                        val aliasName = "$tbl-$colName"
                        columns.add(aliasName)
                        val tblFinal = if (espTableName) "$espChar$tbl$espChar" else tbl
                        selectFields.add("$tblFinal.$colName as $espChar$aliasName$espChar")
                    }
                }
            }
        }

        val sqlFields = selectFields.joinToString(", ")
        return SelectPart(columns, sqlFields)
    }

    private fun toParts(shouldLineBreak: Boolean = false): QueryParts {
        val parts =
            QueryParts(
                this,
                fieldsToSelect,
                tableFrom,
                tableToJoin,
                whereCondition,
                orderBy,
                limitOffset,
                groupBys,
                havingCondition,
                expression
            )
        if (shouldLineBreak) {
            parts.linebreak = "\n"
        }
        return parts
    }

    public fun addExpressionAfter(type: CustomPart, block: QueryBlock) {
        if (this.expression.containsKey(type)) {
            (this.expression[type] as ArrayList).add(block)
        } else {
            this.expression[type] = arrayListOf(block)
        }
    }

    internal fun precompile(shouldLineBreak: Boolean = false): Array<String> {
        val parts = toParts(shouldLineBreak)
        return parts.precompile()
    }

    internal fun compile(
        processor: (Array<String>) -> String,
        shouldLineBreak: Boolean = false
    ): QueryInfo {
        val selectPart = compileSelect()
        val parts = toParts(shouldLineBreak)
        val partsArr = parts.precompile()
        val sql = processor(partsArr)
        return QueryInfo(sql, selectPart.columns, selectPart.sqlFields, parts)
    }

    internal fun compile(shouldLineBreak: Boolean = false): QueryInfo {
        val selectPart = compileSelect()
        val parts = toParts(shouldLineBreak)
        return QueryInfo(parts.compile(), selectPart.columns, selectPart.sqlFields, parts)
    }

    public fun toSql(shouldLineBreak: Boolean = false): String {
        return compile(shouldLineBreak).sql
    }

    override fun toString(): String {
        return compile().sql
    }

    public fun from(table: String): Query {
        tableFrom.add(table)
        return this
    }

    public fun from(tables: List<String>): Query {
        tableFrom.addAll(tables)
        return this
    }

    public fun asTable(table: String): Query {
        tableFrom.add(table)
        return this
    }

    public fun from(table: Query): Query {
        tableFrom.add(table)
        return this
    }

    public fun join(table: String): Query {
        tableToJoin["join-@" + table] = arrayListOf()
        return this
    }

    public fun join(table: Query, asName: String): Query {
        tableToJoin["join-@**" + table.toSql() + "^^$asName"] = arrayListOf()
        return this
    }

    public fun fullJoin(table: String): Query {
        tableToJoin["full-@join-@" + table] = arrayListOf()
        return this
    }

    public fun fullJoin(table: Query, asName: String): Query = fullJoin(table.toSql(), asName)

    public fun fullJoin(table: String, asName: String): Query {
        tableToJoin["full-@join-@**" + table + "^^$asName"] = arrayListOf()
        return this
    }

    public fun leftJoin(table: String): Query {
        tableToJoin["left-@join-@" + table] = arrayListOf()
        return this
    }

    public fun leftJoin(table: Query, asName: String): Query = leftJoin(table.toSql(), asName)

    public fun leftJoin(table: String, asName: String): Query {
        tableToJoin["left-@join-@**" + table + "^^$asName"] = arrayListOf()
        return this
    }

    public fun leftOuterJoin(table: String): Query {
        tableToJoin["left-@outer-@join-@" + table] = arrayListOf()
        return this
    }

    public fun leftOuterJoin(table: Query, asName: String): Query =
        leftOuterJoin(table.toSql(), asName)

    public fun leftOuterJoin(table: String, asName: String): Query {
        tableToJoin["left-@outer-@join-@**" + table + "^^$asName"] = arrayListOf()
        return this
    }

    public fun rightJoin(table: String): Query {
        tableToJoin["right-@join-@" + table] = arrayListOf()
        return this
    }

    public fun rightJoin(table: Query, asName: String): Query = rightJoin(table.toSql(), asName)

    public fun rightJoin(table: String, asName: String): Query {
        tableToJoin["right-@join-@**" + table + "^^$asName"] = arrayListOf()
        return this
    }

    public fun rightOuterJoin(table: String): Query {
        tableToJoin["right-@outer-@join-@" + table] = arrayListOf()
        return this
    }

    public fun rightOuterJoin(table: Query, asName: String): Query =
        rightOuterJoin(table.toSql(), asName)

    public fun rightOuterJoin(table: String, asName: String): Query {
        tableToJoin["right-@outer-@join-@**" + table + "^^$asName"] = arrayListOf()
        return this
    }

    public fun innerJoin(table: String): Query {
        tableToJoin["inner-@join-@" + table] = arrayListOf()
        return this
    }

    public fun innerJoin(table: Query, asName: String): Query = innerJoin(table.toSql(), asName)

    public fun innerJoin(table: String, asName: String): Query {
        tableToJoin["inner-@join-@**" + table + "^^$asName"] = arrayListOf()
        return this
    }

    public fun crossJoin(table: String): Query {
        tableToJoin["cross-@join-@" + table] = arrayListOf()
        return this
    }

    public fun crossJoin(table: Query, asName: String): Query = crossJoin(table.toSql(), asName)

    public fun crossJoin(table: String, asName: String): Query {
        tableToJoin["cross-@join-@**" + table + "^^$asName"] = arrayListOf()
        return this
    }

    public fun on(joinCondition: QueryBlock, useOr: Boolean = false): Query {
        return on(joinCondition.toString(), useOr)
    }

    public fun on(joinCondition: String, useOr: Boolean = false): Query {
        if (tableToJoin.size > 0) {
            val tblName = tableToJoin.entries.last().key
            if (useOr) {
                tableToJoin[tblName]?.add(Or(joinCondition))
            } else {
                tableToJoin[tblName]?.add(And(joinCondition))
            }
        }
        return this
    }

    public fun onAnd(joinCondition: String): Query {
        if (tableToJoin.size > 0) {
            val tblName = tableToJoin.entries.last().key
            tableToJoin[tblName]?.add(And(joinCondition))
        }
        return this
    }

    public fun onOr(joinCondition: String): Query {
        if (tableToJoin.size > 0) {
            val tblName = tableToJoin.entries.last().key
            tableToJoin[tblName]?.add(Or(joinCondition))
        }
        return this
    }

    public fun on(joinConditions: List<Condition>): Query {
        if (tableToJoin.size > 0) {
            val tblName = tableToJoin.entries.last().key
            tableToJoin[tblName]?.addAll(joinConditions)
        }
        return this
    }

    public fun on(joinConditions: List<String>, useOr: Boolean = false): Query {
        if (tableToJoin.size > 0) {
            val tblName = tableToJoin.entries.last().key
            for (joinCondition in joinConditions) {
                if (useOr) {
                    tableToJoin[tblName]?.add(Or(joinCondition))
                } else {
                    tableToJoin[tblName]?.add(And(joinCondition))
                }
            }
        }
        return this
    }

    public fun where(vararg blocks: QueryBlock): Query {
        return whereAnd(*blocks)
    }

    public fun whereAnd(vararg blocks: QueryBlock): Query {
        (blocks as Array<QueryBlock>).forEach { whereCondition.add(And(it.toString())) }
        return this
    }

    public fun whereOr(vararg blocks: QueryBlock): Query {
        (blocks as Array<QueryBlock>).forEach { whereCondition.add(Or(it.toString())) }
        return this
    }

    public fun whereMix(vararg conditions: Any): Query {
        (conditions as Array<Any>).forEach {
            if (it is String) {
                where(it)
            } else if (it is QueryBlock) {
                where(it)
            }
        }
        return this
    }

    public fun where(vararg blocks: Any): Query {
        (blocks as Array<Any>).forEach { whereCondition.add(And(it.toString())) }
        return this
    }

    public fun where(vararg blocks: String): Query {
        (blocks as Array<String>).forEach { whereCondition.add(And(it)) }
        return this
    }

    public fun where(queryBlock: QueryBlock, useOr: Boolean = false): Query {
        return where(queryBlock.toString(), useOr)
    }

    public fun whereAnd(queryBlock: QueryBlock): Query {
        return whereAnd(queryBlock.toString())
    }

    public fun whereOr(queryBlock: QueryBlock): Query {
        return whereOr(queryBlock.toString())
    }

    public fun where(condition: String, useOr: Boolean = false): Query {
        if (useOr) {
            whereCondition.add(Or(condition))
        } else {
            whereCondition.add(And(condition))
        }
        return this
    }

    public fun whereAnd(condition: String): Query {
        whereCondition.add(And(condition))
        return this
    }

    public fun whereOr(condition: String): Query {
        whereCondition.add(Or(condition))
        return this
    }

    public fun where(condition: List<Condition>): Query {
        whereCondition.addAll(condition)
        return this
    }

    public fun where(conditions: List<String>, useOr: Boolean = false): Query {
        for (condition in conditions) {
            if (useOr) {
                whereCondition.add(Or(condition))
            } else {
                whereCondition.add(And(condition))
            }
        }
        return this
    }

    public fun groupByMain(vararg fields: String): Query {
        addExpressionAfter(
            CustomPart.WHERE,
            QueryBlock("GROUP BY", if (fields.size == 1) fields[0] else fields.joinToString(", "))
        )
        return this
    }

    public fun groupBy(vararg fields: String): Query {
        groupBys.addAll(fields as Array<String>)
        return this
    }

    public fun groupBy(field: String): Query {
        groupBys.add(field)
        return this
    }

    public fun groupBy(fields: List<String>): Query {
        groupBys.addAll(fields)
        return this
    }

    public fun havingMix(vararg conditions: Any): Query {
        (conditions as Array<Any>).forEach {
            if (it is String) {
                having(it)
            } else if (it is QueryBlock) {
                having(it)
            }
        }
        return this
    }

    public fun having(vararg conditions: String): Query {
        return havingAnd(*conditions)
    }

    public fun havingAnd(vararg conditions: String): Query {
        (conditions as Array<String>).forEach { havingCondition.add(And(it)) }
        return this
    }

    public fun havingOr(vararg conditions: String): Query {
        (conditions as Array<String>).forEach { havingCondition.add(Or(it)) }
        return this
    }

    public fun having(vararg blocks: QueryBlock): Query {
        return havingAnd(*blocks)
    }

    public fun havingAnd(vararg blocks: QueryBlock): Query {
        (blocks as Array<QueryBlock>).forEach { havingCondition.add(And(it.toString())) }
        return this
    }

    public fun havingOr(vararg blocks: QueryBlock): Query {
        (blocks as Array<QueryBlock>).forEach { havingCondition.add(Or(it.toString())) }
        return this
    }

    public fun having(queryBlock: QueryBlock, useOr: Boolean = false): Query {
        return having(queryBlock.toString(), useOr)
    }

    public fun havingAnd(queryBlock: QueryBlock): Query {
        return havingAnd(queryBlock.toString())
    }

    public fun havingOr(queryBlock: QueryBlock): Query {
        return havingOr(queryBlock.toString())
    }

    public fun having(condition: String, useOr: Boolean = false): Query {
        if (useOr) {
            havingCondition.add(Or(condition))
        } else {
            havingCondition.add(And(condition))
        }
        return this
    }

    public fun havingAnd(condition: String): Query {
        havingCondition.add(And(condition))
        return this
    }

    public fun havingOr(condition: String): Query {
        havingCondition.add(Or(condition))
        return this
    }

    public fun having(condition: List<Condition>): Query {
        havingCondition.addAll(condition)
        return this
    }

    public fun order(vararg fields: String): Query {
        return order(listOf(*fields))
    }

    public fun order(vararg fields: Sort): Query {
        return order(listOf(*fields))
    }

    public fun order(field: String, useDesc: Boolean = false): Query {
        if (useDesc) {
            orderBy.add(Desc(field))
        } else {
            orderBy.add(Asc(field))
        }
        return this
    }

    public fun orderAsc(field: String): Query {
        orderBy.add(Asc(field))
        return this
    }

    public fun orderDesc(field: String): Query {
        orderBy.add(Desc(field))
        return this
    }

    public fun order(fields: List<Sort>): Query {
        orderBy.addAll(fields)
        return this
    }

    public fun order(fields: List<String>, useDesc: Boolean = false): Query {
        for (field in fields) {
            if (useDesc) {
                orderBy.add(Desc(field))
            } else {
                orderBy.add(Asc(field))
            }
        }
        return this
    }

    public fun limit(pageSize: Int, offset: Int = 0): Query {
        limitOffset = arrayOf(pageSize, offset)
        return this
    }
}
