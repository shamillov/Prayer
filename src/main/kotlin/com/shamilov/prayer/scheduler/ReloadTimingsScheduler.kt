package com.shamilov.prayer.scheduler

import com.shamilov.prayer.data.repository.TimingsRepository
import com.shamilov.prayer.utils.currentEpochSeconds
import com.shamilov.prayer.utils.localDateTime
import com.shamilov.prayer.utils.toInstant
import kotlinx.datetime.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * @author Shamilov on 20.01.2024
 */
object ReloadTimingsScheduler {

    @Volatile
    private var isScheduled = false

    private val schedule by lazy { Executors.newSingleThreadScheduledExecutor() }

    fun schedule(city: String, country: String) {
        if (!isScheduled) {
            isScheduled = true

            val reminder = {
                TimingsRepository.loadLimits(city, country)
            }

            val delay = localDateTime.date
                .plus(1, DateTimeUnit.DAY)
                .atTime(0,1)
                .toInstant(TimeZone.currentSystemDefault())

            val initDelay = delay - currentEpochSeconds.toInstant()

            schedule.scheduleAtFixedRate(reminder, initDelay.inWholeMinutes, 24 * 60, TimeUnit.MINUTES)
        }
    }
}
