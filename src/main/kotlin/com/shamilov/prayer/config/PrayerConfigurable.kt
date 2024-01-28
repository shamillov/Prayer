package com.shamilov.prayer.config

import com.intellij.openapi.options.Configurable
import com.shamilov.prayer.data.repository.CountriesRepository
import com.shamilov.prayer.data.repository.TimingsRepository
import com.shamilov.prayer.persistence.location.LocationStore
import com.shamilov.prayer.persistence.timings.TimingsStore
import javax.swing.JComponent

/**
 * @author Shamilov on 16.01.2024
 */
internal class PrayerConfigurable : Configurable {

    private companion object {
        const val DISPLAY_NAME = "Prayer"
    }

    private val countriesRepository = CountriesRepository()
    private val locationStore = LocationStore.instance

    private val component = PrayerSettingsComponent()

    init {
        component.setCountries(countriesRepository.getCountries())
        component.setCity(locationStore.city)
        component.setCountry(locationStore.country)
        TimingsStore.instance.state.timings?.let {
            component.setTimings(it)
        }
    }

    override fun createComponent(): JComponent {
        return component.getComponent()
    }

    override fun isModified(): Boolean {
        val city = locationStore.city.orEmpty()
        val country = locationStore.country.orEmpty()

        return component.getCity()!= city || component.getCountry() != country
    }

    override fun reset() {
        component.reset(
            locationStore.city,
            locationStore.country
        )
    }

    override fun apply() {
        component.setLoading(true)

        val city = component.getCity()
        val country = component.getCountry()

        locationStore.city = city
        locationStore.country = country

        TimingsRepository.loadLimits(
            city = component.getCity(),
            country = component.getCountry(),
        ) { result ->
            component.setLoading(false)
            result.onSuccess { timings ->
                component.setTimings(timings)
            }
        }
    }

    override fun getDisplayName(): String {
        return DISPLAY_NAME
    }
}