package com.itsbaris.cs4520.a5_inandioglu_6696.viewmodel

import com.itsbaris.cs4520.a5_inandioglu_6696.model.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val syncStatus: String = "Offline / Sync Disabled",
)
