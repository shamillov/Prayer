package com.shamilov.prayer.persistence.timings

import com.shamilov.prayer.entity.Timings
import java.io.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
internal class TimingsState(
    var timings: Timings? = null,
) : Serializable
