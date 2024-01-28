package com.shamilov.prayer.config

import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.JBLabel
import com.shamilov.prayer.entity.Countries
import com.shamilov.prayer.entity.Timings
import org.jdesktop.swingx.VerticalLayout
import java.awt.FlowLayout
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*

/**
 * @author Shamilov on 16.01.2024
 */
internal class PrayerSettingsComponent {

    private val mainComponent = JPanel()
    private val settingsPanel = JPanel()
    private val timingPanel = JPanel()
    private val cityTextField = JTextField(10)
    private val countriesComboBox = ComboBox<String>()
    private val progressBar = JProgressBar().apply {
        isVisible = false
    }
    private val dateLabel = JBLabel("Prayer schedule for today:").apply {
        isVisible = false
        font = Font("Date", Font.PLAIN, 14)
    }

    init {
        configureSettingsPanel()
        configureTimingPanel()

        mainComponent.apply {
            layout = VerticalLayout(20)
            add(settingsPanel)
            add(progressBar)
            add(dateLabel)
            add(timingPanel)
        }
    }

    fun getComponent(): JComponent = mainComponent

    fun setTimings(timings: Timings) {
        progressBar.isVisible = false

        if (timings.timingsOfDay.isNotEmpty()) {
            dateLabel.isVisible = true
            dateLabel.text = "Prayer schedule for today (${timings.date}):"
            timingPanel.removeAll()

            timings.timingsOfDay.forEach { timing ->
                timingPanel.add(JBLabel(timing.name!!))
                timingPanel.add(JBLabel(timing.time!!))
            }
            timingPanel.updateUI()
        }
    }

    fun setCountries(countries: Countries) {
        countries.countries.forEach {
            countriesComboBox.addItem(it.name)
        }
    }

    fun reset(city: String?, country: String?) {
        cityTextField.text = city
        countriesComboBox.selectedItem = country
    }

    fun setLoading(visible: Boolean) {
        progressBar.isVisible = visible
    }

    fun getCity(): String = cityTextField.text.orEmpty()

    fun setCity(city: String?) {
        cityTextField.text = city
    }

    fun getCountry(): String = countriesComboBox.item.orEmpty()

    fun setCountry(country: String?) {
        countriesComboBox.item = country
    }

    private fun configureSettingsPanel() = with(settingsPanel) {
        layout = FlowLayout(FlowLayout.LEADING, 10, 10)

        add(JBLabel("City:"))
        add(cityTextField)
        add(JBLabel("Country:"))
        add(countriesComboBox)
    }

    private fun configureTimingPanel() {
        timingPanel.add(Box.createVerticalStrut(10))
        timingPanel.layout = GridLayout(5, 2, 0, 20)
    }
}