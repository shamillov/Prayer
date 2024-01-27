package com.shamilov.prayer.notification

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * @author Shamilov on 19.01.2024
 */
private const val SET_NOTIFICATION_GROUP = "SET_NOTIFICATION_GROUP"
private const val NOTIFICATION_TITLE = "Prayer"
private const val NOTIFICATION_CONTENT = "You have to select location to get the prayer schedule"

internal class SetLocationNotification(
    setLocation: () -> Unit,
) : Notification(
    SET_NOTIFICATION_GROUP,
    NOTIFICATION_TITLE,
    NOTIFICATION_CONTENT,
    NotificationType.WARNING
) {
    init {
        addAction(object : AnAction("Set Location") {
            override fun actionPerformed(e: AnActionEvent) {
                setLocation()
            }
        })
    }
}
