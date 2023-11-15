package io.zeko.db.sql

public open class QueryBlock {
    private var left: String = ""
    private var right: String = ""
    private var center: String = ""

    public constructor(left: String, center: String, right: String) {
        this.center = center
        this.left = left
        this.right = right
    }

    public constructor(left: String, right: String) {
        this.left = left
        this.right = right
    }

    public constructor(center: String) {
        this.center = center
    }

    public open fun getStatement(): String = "$left $center $right"

    override fun toString(): String {
        return "$left $center $right"
    }
}
