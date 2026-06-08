package com.itsbaris.cs4520.a5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsbaris.cs4520.a5.model.Task
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    private val _events = Channel<TaskEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    /**
     * 1. What: Creates a new task after validating the required task name.
     * 2. Who: Called by the add task screen when the user taps Save Task.
     * 3. When: Executed after the user submits the add task form.
     */
    fun addTask(
        name: String,
        description: String,
    ): Boolean {
        val trimmedName = name.trim()
        if (trimmedName.isBlank()) {
            showSnackbar("Task name cannot be empty")
            return false
        }

        val task =
            Task(
                name = trimmedName,
                description = description.trim(),
            )
        _uiState.update { state -> state.copy(tasks = state.tasks + task) }
        return true
    }

    /**
     * 1. What: Updates an existing task after validating the required task name.
     * 2. Who: Called by the edit task screen when the user taps Save Task.
     * 3. When: Executed after the user submits edits for an existing task.
     */
    fun updateTask(
        taskId: String,
        name: String,
        description: String,
    ): Boolean {
        val trimmedName = name.trim()
        if (trimmedName.isBlank()) {
            showSnackbar("Task name cannot be empty")
            return false
        }

        _uiState.update { state ->
            state.copy(
                tasks =
                    state.tasks.map { task ->
                        if (task.id == taskId) {
                            task.copy(
                                name = trimmedName,
                                description = description.trim(),
                            )
                        } else {
                            task
                        }
                    },
            )
        }
        return true
    }

    /**
     * 1. What: Deletes a task from the current task list.
     * 2. Who: Called by the task list screen after delete confirmation.
     * 3. When: Executed when the user confirms deletion in the dialog.
     */
    fun deleteTask(taskId: String) {
        _uiState.update { state ->
            state.copy(tasks = state.tasks.filterNot { task -> task.id == taskId })
        }
    }

    /**
     * 1. What: Toggles the completed state for a task.
     * 2. Who: Called by the task list screen checkbox.
     * 3. When: Executed when the user marks a task complete or incomplete.
     */
    fun toggleTaskCompleted(taskId: String) {
        _uiState.update { state ->
            state.copy(
                tasks =
                    state.tasks.map { task ->
                        if (task.id == taskId) {
                            task.copy(isCompleted = !task.isCompleted)
                        } else {
                            task
                        }
                    },
            )
        }
    }

    /**
     * 1. What: Finds a task by ID from the current StateFlow value.
     * 2. Who: Called by the add/edit route to pre-fill edit fields.
     * 3. When: Executed when the form screen opens in edit mode.
     */
    fun getTask(taskId: String?): Task? = _uiState.value.tasks.firstOrNull { task -> task.id == taskId }

    /**
     * 1. What: Stores the current network sync status string in UI state.
     * 2. Who: Called by MainActivity or NetworkReceiver after connectivity checks.
     * 3. When: Executed on app start and whenever connectivity changes.
     */
    fun updateSyncStatus(isConnected: Boolean) {
        val status = if (isConnected) "Online / Sync Enabled" else "Offline / Sync Disabled"
        _uiState.update { state -> state.copy(syncStatus = status) }
    }

    /**
     * 1. What: Sends a snackbar event through the one-shot event channel.
     * 2. Who: Called by ViewModel validation logic.
     * 3. When: Executed when the UI should show a transient error message.
     */
    private fun showSnackbar(message: String) {
        viewModelScope.launch {
            _events.send(TaskEvent.ShowSnackbar(message))
        }
    }
}
