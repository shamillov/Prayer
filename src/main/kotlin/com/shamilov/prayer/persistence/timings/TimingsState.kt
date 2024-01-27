package com.shamilov.prayer.persistence.timings

import com.intellij.openapi.components.BaseState
import com.intellij.util.xmlb.annotations.OptionTag
import com.shamilov.prayer.entity.Timing
import kotlinx.datetime.LocalDate
import java.io.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
internal class TimingsState(
    var timings: List<Timing> = emptyList(),
    @OptionTag(converter = LocalDateConverter::class)
    var localDate: LocalDate? = null,
) : BaseState(), Serializable
