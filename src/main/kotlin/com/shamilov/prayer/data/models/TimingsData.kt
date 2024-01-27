package com.shamilov.prayer.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
@Serializable
data class TimingsData(
    @SerialName("Sunset") val sunset: String,
    @SerialName("Asr") val asr: String,
    @SerialName("Isha") val isha: String,
    @SerialName("Fajr") val fajr: String,
    @SerialName("Dhuhr") val dhuhr: String,
    @SerialName("Maghrib") val maghrib: String,
    @SerialName("Lastthird") val lastthird: String,
    @SerialName("Firstthird") val firstthird: String,
    @SerialName("Sunrise") val sunrise: String,
    @SerialName("Midnight") val midnight: String,
    @SerialName("Imsak") val imsak: String
)
