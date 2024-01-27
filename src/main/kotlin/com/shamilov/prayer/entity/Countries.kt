package com.shamilov.prayer.entity

import kotlinx.serialization.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
@Serializable
internal data class Countries(
    val countries: List<Country>,
)

@Serializable
internal data class Country(
    val code: String,
    val name: String,
)
