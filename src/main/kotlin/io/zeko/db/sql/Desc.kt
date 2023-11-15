package io.zeko.db.sql

public class Desc(fieldName: String) : Sort(fieldName) {

    override fun getDirection(): String {
        return "DESC"
    }
}
