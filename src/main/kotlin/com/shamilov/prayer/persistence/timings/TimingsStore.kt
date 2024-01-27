package com.shamilov.prayer.persistence.timings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.XmlSerializerUtil
import com.intellij.util.xmlb.annotations.OptionTag
import com.shamilov.prayer.entity.Timing
import kotlinx.datetime.LocalDate

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

    @OptionTag(converter = LocalDateConverter::class)
    var lastLocalDate: LocalDate? = null

    var timings: List<Timing> = emptyList()

    override fun getState(): TimingsStore {
        return this
    }

    override fun loadState(value: TimingsStore) {
        XmlSerializerUtil.copyBean(value, this)
    }

}
