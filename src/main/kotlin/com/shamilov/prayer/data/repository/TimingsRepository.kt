package com.shamilov.prayer.data.repository

import com.intellij.openapi.project.ProjectManager
import com.shamilov.prayer.data.loadTimings
import com.shamilov.prayer.data.mapper.TimingsMapper
import com.shamilov.prayer.entity.Timings
import com.shamilov.prayer.notification.ReminderNotification
import com.shamilov.prayer.persistence.location.LocationStore
import com.shamilov.prayer.persistence.timings.TimingsStore
import com.shamilov.prayer.scheduler.NotificationsScheduler
import com.shamilov.prayer.utils.getFormattedDate

/**
 * @author Shamilov on 16.01.2024
 */
internal class TimingsRepository {

    fun loadLimits(city: String, country: String, callback: (Result<Timings>) -> Unit) {
        loadTimings(
            date = getFormattedDate(),
            city = city,
            country = country
        ) { result ->
            result.fold(
                onSuccess = {
                    if (it.code == 200) {
                        val timings = TimingsMapper.map(it)

                        val locationStore = LocationStore.instance
                        locationStore.city = city
                        locationStore.country = country

                        TimingsStore.instance.timings = timings.timingsOfDay
                        TimingsStore.instance.lastLocalDate = timings.date

                        NotificationsScheduler.schedule(timings.timingsOfDay)
                        ProjectManager.getInstance().openProjects.forEach {
                            ReminderNotification("Loaded ${timings.date}").notify(it)
                        }

                        callback(Result.success(timings))
                    } else {
                        callback(Result.failure(Throwable(it.status)))
                    }
                },
                onFailure = {
                    callback(Result.failure(it))
                }
            )
        }
    }
}
