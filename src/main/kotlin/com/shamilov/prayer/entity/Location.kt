package com.shamilov.prayer.entity

import java.io.Serializable

/**
 * @author Shamilov on 16.01.2024
 */
internal data class Location(
    val city: String,
    val country: String,
) : Serializable
