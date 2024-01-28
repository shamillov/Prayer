package com.shamilov.prayer.persistence.timings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import com.shamilov.prayer.entity.Timings

/**
 * @author Shamilov on 16.01.2024
 */
@State(
    name = "Timings",
    storages = [Storage(value = "timings.xml")]
)
internal class TimingsStore : PersistentStateComponent<TimingsStore> {

    companion object {
        val instance: TimingsStore
            get() = ApplicationManager.getApplication().getService(TimingsStore::class.java)
    }

    var timings: Timings? = null

    override fun getState(): TimingsStore {
        return this
    }

    override fun loadState(value: TimingsStore) {
        XmlSerializerUtil.copyBean(value, this)
    }
}
