package com.shamilov.prayer.persistence.preferences

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

/**
 * @author Shamilov on 19.03.2024
 */
internal interface PreferencesStore {
    var openingSoundEnabled: Boolean
    var notificationSoundEnabled: Boolean

    companion object {
        val instance: PreferencesStore
            get() = ApplicationManager.getApplication().getService(PreferencesStoreImpl::class.java)
    }
}

@State(
    name = "Preferences",
    storages = [Storage(value = "preferences.xml")]
)
private class PreferencesStoreImpl : SimplePersistentStateComponent<PreferencesState>(PreferencesState()), PreferencesStore {

    override var openingSoundEnabled: Boolean
        get() = state.openingSoundEnabled
        set(value) {
            state.openingSoundEnabled = value
        }

    override var notificationSoundEnabled: Boolean
        get() = state.notificationSoundEnabled
        set(value) {
            state.notificationSoundEnabled = value
        }
}
