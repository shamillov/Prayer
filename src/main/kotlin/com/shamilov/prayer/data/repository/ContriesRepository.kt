package com.shamilov.prayer.data.repository

import com.shamilov.prayer.entity.Countries
import com.shamilov.prayer.data.json
import kotlinx.serialization.decodeFromString

/**
 * @author Shamilov on 16.01.2024
 */
internal class CountriesRepository {
    fun getCountries(): Countries {
        return json.decodeFromString<Countries>(
            javaClass.classLoader
                .getResource("countries.json")!!
                .readText()
        )
    }
}
