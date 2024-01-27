package com.shamilov.prayer.notification

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType

/**
 * @author Shamilov on 16.01.2024
 */

private const val REMIND_NOTIFICATION_GROUP = "REMIND_NOTIFICATION_GROUP"
private const val NOTIFICATION_TITLE = "Time to pray"

internal class ReminderNotification(
    content: String,
): Notification(
    REMIND_NOTIFICATION_GROUP,
    NOTIFICATION_TITLE,
    content,
    NotificationType.INFORMATION,
)
