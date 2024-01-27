package com.shamilov.prayer.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
@Serializable
internal data class PrayerData(
    val date: DateData,
    val meta: MetaData,
    val timings: TimingsData,
)

@Serializable
internal data class DateData(
    val readable: String,
    val hijri: HijriData,
    val gregorian: GregorianData,
    val timestamp: String
)

@Serializable
internal data class GregorianData(
    val date: String,
    val month: GregorianMonthData,
    val year: String,
    val format: String,
    val weekday: GregorianWeekdayData,
    val designation: DesignationData,
    val day: String
)

@Serializable
internal data class DesignationData(
    val expanded: String,
    val abbreviated: String
)

@Serializable
internal data class GregorianMonthData(
    val number: Long,
    val en: String
)

@Serializable
internal data class GregorianWeekdayData(
    val en: String
)

@Serializable
internal data class HijriData(
    val date: String,
    val month: HijriMonthData,
    val year: String,
    val format: String,
    val weekday: HijriWeekdayData,
    val designation: DesignationData,
    val day: String
)

@Serializable
internal data class HijriMonthData(
    val number: Long,
    val ar: String,
    val en: String
)

@Serializable
internal data class HijriWeekdayData(
    val ar: String,
    val en: String
)

@Serializable
internal data class MetaData(
    val method: MethodData,
    val offset: Map<String, Long>,
    val school: String,
    val timezone: String,
    val midnightMode: String,
    val latitude: Double,
    val longitude: Double,
    val latitudeAdjustmentMethod: String
)

@Serializable
internal data class MethodData(
    val name: String,
    val location: LocationData,
    val id: Long,
    val params: ParamsData
)

@Serializable
internal data class LocationData(
    val latitude: Double,
    val longitude: Double
)

@Serializable
internal data class ParamsData(
    @SerialName("Isha")
    val isha: String,
    @SerialName("Fajr")
    val fajr: Double
)
