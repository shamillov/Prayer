package com.shamilov.prayer.entity

import com.intellij.util.xmlb.annotations.OptionTag
import com.shamilov.prayer.persistence.timings.InstantConverter
import com.shamilov.prayer.persistence.timings.LocalDateConverter
import com.shamilov.prayer.utils.currentEpochSeconds
import com.shamilov.prayer.utils.toInstant
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import java.io.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
internal data class Timings(
    var timingsOfDay: List<Timing> = emptyList(),
    @OptionTag(converter = LocalDateConverter::class)
    var date: LocalDate? = null,
) : Serializable

internal data class Timing(
    var name: String? = null,
    var time: String? = null,
    @OptionTag(converter = InstantConverter::class)
    var instant: Instant? = null,
) : Serializable {

    fun isExpired(): Boolean = currentEpochSeconds.toInstant() > instant!!
}
