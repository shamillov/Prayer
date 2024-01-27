package com.shamilov.prayer.persistence.timings

import com.intellij.util.xmlb.Converter
import kotlinx.datetime.LocalDate

/**
 * @author Shamilov on 24.01.2024
 */

class LocalDateConverter : Converter<LocalDate>() {
    override fun fromString(value: String): LocalDate {
        return LocalDate.parse(value)
    }

    override fun toString(value: LocalDate): String {
        return value.toString()
    }
}
