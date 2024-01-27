package com.shamilov.prayer.persistence.location

import com.intellij.openapi.components.BaseState

/**
 * @author Shamilov on 16.01.2024
 */
internal class LocationState : BaseState() {
    var city by string()
    var country by string()
}
