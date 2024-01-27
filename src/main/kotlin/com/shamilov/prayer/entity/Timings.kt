package com.shamilov.prayer.entity

import com.intellij.util.xmlb.annotations.OptionTag
import com.shamilov.prayer.persistence.timings.InstantConverter
import com.shamilov.prayer.utils.currentEpochSeconds
import com.shamilov.prayer.utils.toInstant
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import java.io.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
internal data class Timings(
    val timingsOfDay: List<Timing>,
    val date: LocalDate,
) : Serializable

internal data class Timing(
    val name: String = "",
    val time: String = "",
    @OptionTag(converter = InstantConverter::class)
    val instant: Instant? = null,
) : Serializable {

    fun isExpired(): Boolean = currentEpochSeconds.toInstant() > instant!!
}
