package io.zeko.db.sql

public abstract class Sort(internal var fieldName: String) {
    public open fun getDirection(): String = "ASC"
}
