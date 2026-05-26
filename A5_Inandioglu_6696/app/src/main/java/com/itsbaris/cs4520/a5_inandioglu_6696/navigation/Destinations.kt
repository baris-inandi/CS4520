package com.itsbaris.cs4520.a5_inandioglu_6696.navigation

import kotlinx.serialization.Serializable

/**
 * 1. What: Type-safe route for the task list screen.
 * 2. Who: Used by TaskNavigation when defining and opening the start destination.
 * 3. When: Used when the app launches or returns to the task list.
 */
@Serializable
object TaskList

/**
 * 1. What: Type-safe route for adding or editing a task.
 * 2. Who: Used by TaskNavigation when the user taps add or edit.
 * 3. When: Used when opening the task form, with taskId present only for edits.
 */
@Serializable
data class AddEditTask(
    val taskId: String? = null,
)

/**
 * 1. What: Type-safe route for the task statistics screen.
 * 2. Who: Used by TaskNavigation when the user opens task stats.
 * 3. When: Used when navigating from the task list to the stats screen.
 */
@Serializable
object TaskStats
