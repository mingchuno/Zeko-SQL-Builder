package io.zeko.db.sql.connections

public interface DBSession {
    public fun pool(): DBPool

    public fun connection(): DBConn

    public fun rawConnection(): Any

    public fun setQueryLogger(logger: DBLogger): DBSession

    public fun getQueryLogger(): DBLogger?

    public suspend fun <A> once(operation: suspend (DBSession) -> A): A

    public suspend fun <A> retry(
        numRetries: Int,
        delayTry: Long = 0,
        operation: suspend (DBSession) -> A
    )

    public suspend fun <A> transaction(operation: suspend (DBSession) -> A): A

    public suspend fun <A> transactionOpen(operation: suspend (DBSession) -> A): A

    public suspend fun <A> transaction(
        numRetries: Int,
        delayTry: Long = 0,
        operation: suspend (DBSession) -> A
    )

    public suspend fun close()

    public suspend fun insert(
        sql: String,
        params: List<Any?>,
        closeStatement: Boolean = true,
        closeConn: Boolean = false
    ): List<*>

    public suspend fun update(
        sql: String,
        params: List<Any?>,
        closeStatement: Boolean = true,
        closeConn: Boolean = false
    ): Int

    public suspend fun <T> queryPrepared(
        sql: String,
        params: List<Any?>,
        dataClassHandler: (dataMap: Map<String, Any?>) -> T,
        closeStatement: Boolean = true,
        closeConn: Boolean = false
    ): List<T>

    public suspend fun queryPrepared(sql: String, params: List<Any?>): Any

    public suspend fun queryPrepared(
        sql: String,
        params: List<Any?>,
        columns: List<String>,
        closeConn: Boolean = false
    ): List<LinkedHashMap<String, Any?>>

    public suspend fun <T> query(
        sql: String,
        dataClassHandler: (dataMap: Map<String, Any?>) -> T,
        closeStatement: Boolean = true,
        closeConn: Boolean = false
    ): List<T>

    public suspend fun query(sql: String): Any

    public suspend fun query(
        sql: String,
        columns: List<String>,
        closeConn: Boolean = false
    ): List<LinkedHashMap<String, Any?>>
}
