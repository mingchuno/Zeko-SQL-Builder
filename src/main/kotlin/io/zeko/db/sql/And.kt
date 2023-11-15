package io.zeko.db.sql

public class And(logic: String) : Condition(logic) {

    override fun getOperator(): String = "AND"
}
