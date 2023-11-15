package io.zeko.db.sql.connections

public enum class DBLogLevel(public val level: Int) {
    ALL(5),
    DEBUG(4),
    INFO(3),
    WARN(2),
    ERROR(1),
    OFF(0)
}
