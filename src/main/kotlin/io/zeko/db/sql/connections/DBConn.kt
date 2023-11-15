package io.zeko.db.sql.connections

public interface DBConn {
    public suspend fun beginTx()

    public suspend fun endTx()

    public suspend fun commit()

    public suspend fun close()

    public suspend fun rollback()

    public fun raw(): Any
}
