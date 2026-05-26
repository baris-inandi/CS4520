package com.itsbaris.cs4520.a5_inandioglu_6696.viewmodel

sealed class TaskEvent {
    data class ShowSnackbar(
        val message: String,
    ) : TaskEvent()
}
