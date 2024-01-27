package com.shamilov.prayer.data.mapper

import com.shamilov.prayer.data.models.PrayerData
import com.shamilov.prayer.data.models.ResponseData
import com.shamilov.prayer.entity.Timing
import com.shamilov.prayer.entity.Timings
import com.shamilov.prayer.utils.toInstant
import kotlinx.datetime.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * @author Shamilov on 16.01.2024
 */
internal object TimingsMapper {

    private const val FAJR = "Fajr"
    private const val DHUHR = "Dhuhr"
    private const val ASR = "Asr"
    private const val MAGHRIB = "Margrib"
    private const val ISHA = "Isha"

    private const val LOCAL_TIME_PATTER = "HH:mm"

    fun map(response: ResponseData): Timings {
        return map(response.data)
    }

    private fun map(data: PrayerData): Timings {
        val localDate = getLocalDateTime(data.date.timestamp)
        val timings = data.timings
        return Timings(
            timingsOfDay = buildList {
                add(Timing(FAJR, timings.fajr, getInstant(timings.fajr, localDate)))
                add(Timing(DHUHR, timings.dhuhr, getInstant(timings.dhuhr, localDate)))
                add(Timing(ASR, timings.asr, getInstant(timings.asr, localDate)))
                add(Timing(MAGHRIB, timings.maghrib, getInstant(timings.maghrib, localDate)))
                add(Timing(ISHA, timings.isha, getInstant(timings.isha, localDate)))
            },
            date = localDate
        )
    }

    private fun getLocalDateTime(timestamp: String) = timestamp
        .toLong()
        .toInstant()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    private fun getInstant(time: String, localDate: LocalDate): Instant {
        val localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern(LOCAL_TIME_PATTER))

        return LocalDateTime(
            year = localDate.year,
            monthNumber = localDate.monthNumber,
            dayOfMonth = localDate.dayOfMonth,
            hour = localTime.hour,
            minute = localTime.minute
        ).toInstant(TimeZone.currentSystemDefault())
    }
}
