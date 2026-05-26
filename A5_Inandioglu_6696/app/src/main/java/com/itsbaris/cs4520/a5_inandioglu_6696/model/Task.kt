package com.itsbaris.cs4520.a5_inandioglu_6696.model

import kotlinx.serialization.Serializable

/**
 * 1. What: Data class representing one task item in the task manager.
 * 2. Who: Used by TaskViewModel and task UI screens.
 * 3. When: Created when a user saves a new task or updates an existing one.
 */
@Serializable
data class Task(
    val id: String =
        java.util.UUID
            .randomUUID()
            .toString(),
    val name: String,
    val description: String = "",
    val isCompleted: Boolean = false,
)
