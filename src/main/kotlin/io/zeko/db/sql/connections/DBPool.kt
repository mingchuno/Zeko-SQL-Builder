package io.zeko.db.sql.connections

public interface DBPool {
    public suspend fun createConnection(): DBConn

    public fun getInsertStatementMode(): Int

    public fun setInsertStatementMode(mode: Int)
}
