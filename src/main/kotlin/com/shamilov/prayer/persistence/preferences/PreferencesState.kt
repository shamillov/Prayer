package com.shamilov.prayer.persistence.preferences

import com.intellij.openapi.components.BaseState

/**
 * @author Shamilov on 19.03.2024
 */
internal class PreferencesState : BaseState() {
    var openingSoundEnabled: Boolean by property(false)
    var notificationSoundEnabled: Boolean by property(false)
}
