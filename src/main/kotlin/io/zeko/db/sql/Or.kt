package io.zeko.db.sql

public class Or(logic: String) : Condition(logic) {

    override fun getOperator(): String = "OR"
}
