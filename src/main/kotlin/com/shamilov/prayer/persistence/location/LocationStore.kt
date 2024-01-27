package com.shamilov.prayer.persistence.location

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

/**
 * @author Shamilov on 16.01.2024
 */

internal interface LocationStore {
    var city: String?
    var country: String?

    companion object {
        val instance: LocationStore
            get() = ApplicationManager.getApplication().getService(LocationStoreImpl::class.java)
    }
}

@State(
    name = "Location",
    storages = [Storage(value = "location.xml")]
)
private class LocationStoreImpl : SimplePersistentStateComponent<LocationState>(LocationState()), LocationStore {

    override var city: String?
        get() = state.city
        set(value) {
            state.city = value
        }

    override var country: String?
        get() = state.country
        set(value) {
            state.country = value
        }
}
