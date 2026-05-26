package com.itsbaris.cs4520.a5_inandioglu_6696.viewmodel

import com.itsbaris.cs4520.a5_inandioglu_6696.model.Task

/**
 * 1. What: Holds the screen state exposed by TaskViewModel.
 * 2. Who: Observed by TaskNavigation and passed down to task screens.
 * 3. When: Updated whenever tasks or network sync status change.
 */
data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val syncStatus: String = "Offline / Sync Disabled",
)
