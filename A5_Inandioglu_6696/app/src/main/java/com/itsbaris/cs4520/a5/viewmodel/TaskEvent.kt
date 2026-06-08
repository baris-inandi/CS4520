package com.itsbaris.cs4520.a5.viewmodel

/**
 * 1. What: Represents one-time UI events sent by the TaskViewModel.
 * 2. Who: Collected by TaskNavigation to show transient UI feedback.
 * 3. When: Emitted when validation or permission feedback must be shown once.
 */
sealed class TaskEvent {
    /**
     * 1. What: One-time event requesting a snackbar message.
     * 2. Who: Created by TaskViewModel validation logic.
     * 3. When: Used when the UI should show a short message and not persist it in state.
     */
    data class ShowSnackbar(
        val message: String,
    ) : TaskEvent()
}
