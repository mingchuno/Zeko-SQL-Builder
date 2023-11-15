package io.zeko.db.sql

public abstract class Condition(private var logic: String) {

    public open fun getStatement(): String = logic

    public open fun getOperator(): String = ""
}
