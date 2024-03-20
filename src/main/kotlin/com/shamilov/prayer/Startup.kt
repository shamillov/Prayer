package com.shamilov.prayer

import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.shamilov.prayer.config.PrayerConfigurable
import com.shamilov.prayer.data.repository.TimingsRepository
import com.shamilov.prayer.notification.SetLocationNotification
import com.shamilov.prayer.notification.SoundPlayer
import com.shamilov.prayer.persistence.location.LocationStore
import com.shamilov.prayer.persistence.preferences.PreferencesStore
import com.shamilov.prayer.persistence.timings.TimingsStore
import com.shamilov.prayer.scheduler.NotificationsScheduler
import com.shamilov.prayer.utils.localDateTime

/**
 * @author Shamilov on 16.01.2024
 */
class Startup : StartupActivity {

    override fun runActivity(project: Project) {
        val locationStore = LocationStore.instance
        val timingsStore = TimingsStore.instance
        val preferences = PreferencesStore.instance

        if (preferences.openingSoundEnabled) {
            SoundPlayer.play()
        }

        val city = locationStore.city
        val country = locationStore.country

        if (city != null && country != null) {
            val timings = timingsStore.state.timings

            if (timings?.date == localDateTime.date) {
                NotificationsScheduler.schedule(timings)
            } else {
                TimingsRepository.loadLimits(city, country)
            }
        } else {
            SetLocationNotification {
                ShowSettingsUtil.getInstance().editConfigurable(project, PrayerConfigurable())
            }.notify(project)
        }
    }
}
