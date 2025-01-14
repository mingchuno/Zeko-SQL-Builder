package io.zeko.db.sql.utilities

internal fun String.toSnakeCase(): String {
    var text = ""
    this.forEachIndexed { index, c ->
        if (c.isUpperCase()) {
            if (index > 0) text += "_"
            text += c.toLowerCase()
        } else {
            text += c
        }
    }
    return text
}

internal fun String.toCamelCase(): String {
    if (this.isEmpty()) {
        return ""
    }
    var camelCase = this.substring(0, 1).toLowerCase()

    if (this.length > 1) {
        var wordStart = false

        for (i in 1..(this.length - 1)) {
            val currChar = this[i]
            if (currChar == '_') {
                wordStart = true
            } else {
                if (wordStart) {
                    camelCase += currChar.toUpperCase()
                } else {
                    camelCase += currChar.toLowerCase()
                }
                wordStart = false
            }
        }
    }
    return camelCase
}
