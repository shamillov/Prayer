package com.shamilov.prayer.utils

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * @author Shamilov on 16.01.2024
 */

private const val DAY_MONTH_PATTERN = "d MMMM"
private const val FOR_API_DATE_PATTERN = "DD-MM-YYYY"

val localDateTime: LocalDateTime
    get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

val currentEpochSeconds: Long
    get() = Clock.System.now().epochSeconds

fun LocalDateTime.getDayMonthFormat(): String {
    val javaDateTime = date.toJavaLocalDate()
    val formatter = DateTimeFormatter.ofPattern(DAY_MONTH_PATTERN, Locale.getDefault())

    return javaDateTime.format(formatter)
}

fun Long.toInstant(): Instant = Instant.fromEpochSeconds(this)

fun getFormattedDate(): String = localDateTime
    .date
    .toJavaLocalDate()
    .format(DateTimeFormatter.ofPattern(FOR_API_DATE_PATTERN))
