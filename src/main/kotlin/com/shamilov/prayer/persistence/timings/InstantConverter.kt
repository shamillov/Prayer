package com.shamilov.prayer.persistence.timings

import com.intellij.util.xmlb.Converter
import kotlinx.datetime.Instant

/**
 * @author Shamilov on 24.01.2024
 */
internal class InstantConverter : Converter<Instant>() {

    override fun toString(value: Instant): String {
        return value.epochSeconds.toString()
    }

    override fun fromString(value: String): Instant {
        return Instant.fromEpochSeconds(value.toLong())
    }
}
