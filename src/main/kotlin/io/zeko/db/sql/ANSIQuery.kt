package io.zeko.db.sql

public class ANSIQuery(espChar: String = "\"", asChar: String = "=", espTableName: Boolean = true) :
    Query(espChar, asChar, espTableName)
