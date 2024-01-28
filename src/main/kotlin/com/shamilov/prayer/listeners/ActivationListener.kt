package com.shamilov.prayer.listeners

import com.intellij.openapi.application.ApplicationActivationListener
import com.intellij.openapi.wm.IdeFrame
import com.shamilov.prayer.data.repository.TimingsRepository
import com.shamilov.prayer.persistence.location.LocationStore
import com.shamilov.prayer.persistence.timings.TimingsStore
import com.shamilov.prayer.utils.localDateTime

/**
 * @author Shamilov on 27.01.2024
 */
class ActivationListener : ApplicationActivationListener {
    override fun applicationActivated(ideFrame: IdeFrame) {
        if (!isTimingsRelevance()) {
            loadTimings()
        }
    }

    @Synchronized
    fun loadTimings() {
        if (isTimingsRelevance()) return

        val city = LocationStore.instance.city
        val country = LocationStore.instance.country

        if (city == null || country == null) return

        TimingsRepository.loadLimits(city, country)
    }

    private fun isTimingsRelevance(): Boolean {
        return localDateTime.date == TimingsStore.instance.timings?.date
    }
}
