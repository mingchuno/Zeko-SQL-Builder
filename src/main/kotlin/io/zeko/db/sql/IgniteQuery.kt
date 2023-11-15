package io.zeko.db.sql

public class IgniteQuery(
    espChar: String = "\"",
    asChar: String = "=",
    espTableName: Boolean = true
) : Query(espChar, asChar, espTableName)
