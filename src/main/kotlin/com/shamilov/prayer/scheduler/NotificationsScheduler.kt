package com.shamilov.prayer.scheduler

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.shamilov.prayer.entity.Timing
import com.shamilov.prayer.entity.Timings
import com.shamilov.prayer.notification.ReminderNotification
import com.shamilov.prayer.utils.currentEpochSeconds
import com.shamilov.prayer.utils.toInstant
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * @author Shamilov on 16.01.2024
 */
internal object NotificationsScheduler {

    private val schedule by lazy {
        Executors.newSingleThreadScheduledExecutor()
    }

    private val scheduleList = mutableListOf<ScheduledFuture<*>>()

    @Synchronized
    fun schedule(timings: Timings?) {
        timings ?: return

        scheduleList.forEach { it.cancel(true) }
        scheduleList.clear()

        val localInstant = currentEpochSeconds.toInstant()

        timings.timingsOfDay
            .filter { !it.isExpired() }
            .forEach { timing ->
                val delay = timing.instant!! - localInstant

                val reminder: () -> Unit = {
                    ProjectManager.getInstance().openProjects.forEach { project ->
                        notify(timing, project)
                    }
                }

                val future = schedule.schedule(reminder, delay.inWholeMilliseconds, TimeUnit.MILLISECONDS)
                scheduleList.add(future)
            }
    }

    private fun notify(timing: Timing, project: Project) {
        ReminderNotification("${timing.name} namaz ${timing.time}").notify(project)
    }
}
