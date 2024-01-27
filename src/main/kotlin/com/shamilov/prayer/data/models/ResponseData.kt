package com.shamilov.prayer.data.models

import kotlinx.serialization.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
@Serializable
internal data class ResponseData(
    val code: Int,
    val status: String,
    val data: PrayerData,
)
