package io.zeko.db.sql.exceptions

internal class DuplicateKeyException(
    private val column: String,
    private val entry: String? = null,
    override val message: String? = null
) : Exception() {
    override fun toString(): String {
        if (entry.isNullOrEmpty()) {
            return "Duplicate entry for key '$column'"
        }
        return "Duplicate entry '$entry' for key '$column'"
    }

    override fun hashCode(): Int {
        return column.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other is String) {
            return other == column
        }
        return super.equals(other)
    }
}
