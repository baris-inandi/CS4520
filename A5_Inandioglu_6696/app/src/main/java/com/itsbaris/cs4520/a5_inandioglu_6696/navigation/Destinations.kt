package com.itsbaris.cs4520.a5_inandioglu_6696.navigation

import kotlinx.serialization.Serializable

@Serializable
object TaskList

@Serializable
data class AddEditTask(
    val taskId: String? = null,
)

@Serializable
object TaskStats
