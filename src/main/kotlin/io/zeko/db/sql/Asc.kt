package io.zeko.db.sql

public class Asc : Sort {
    public constructor(fieldName: String) : super(fieldName)

    override fun getDirection(): String {
        return "ASC"
    }
}
