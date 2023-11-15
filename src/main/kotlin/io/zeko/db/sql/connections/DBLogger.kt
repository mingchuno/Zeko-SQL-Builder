package io.zeko.db.sql.connections

public interface DBLogger {
    public fun getSqlLogLevel(): DBLogLevel

    public fun setSqlLogLevel(level: DBLogLevel): DBLogger

    public fun getParamsLogLevel(): DBLogLevel

    public fun setParamsLogLevel(level: DBLogLevel): DBLogger

    public fun setLogLevels(sqlLevel: DBLogLevel, paramsLevel: DBLogLevel): DBLogger

    public fun logQuery(sql: String, params: List<Any?>? = null)

    public fun logError(err: Exception)

    public fun logUnsupportedSql(err: Exception)

    public fun logRetry(numRetriesLeft: Int, err: Exception)
}
