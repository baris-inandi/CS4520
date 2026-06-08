package com.itsbaris.cs4520.a5.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.itsbaris.cs4520.a5.R
import com.itsbaris.cs4520.a5.model.Task

object TaskNotificationHelper {
    private const val CHANNEL_ID = "task_reminders"
    private const val CHANNEL_NAME = "Task Reminders"

    /**
     * 1. What: Creates the high-importance notification channel used for task reminders.
     * 2. Who: Called by MainActivity during app startup.
     * 3. When: Executed before any reminder notification can be posted.
     */
    fun createNotificationChannel(context: Context) {
        val channel =
            NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            ).apply {
                description = "Task reminder notifications"
            }
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    /**
     * 1. What: Posts a high-priority reminder notification for one task.
     * 2. Who: Called by TaskNavigation after notification permission is available.
     * 3. When: Executed when the user taps Remind Me on a task item.
     */
    fun showTaskReminder(
        context: Context,
        task: Task,
    ) {
        val notification =
            NotificationCompat
                .Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_reminder)
                .setContentTitle("Task Reminder")
                .setContentText("Reminder: ${task.name}")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
                .build()

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(task.id.hashCode(), notification)
    }
}
