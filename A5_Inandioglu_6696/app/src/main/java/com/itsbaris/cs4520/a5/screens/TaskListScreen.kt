package com.itsbaris.cs4520.a5.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a5.R
import com.itsbaris.cs4520.a5.model.Task
import com.itsbaris.cs4520.a5.viewmodel.TaskUiState

/**
 * 1. What: Screen that displays the task list and top-level task actions.
 * 2. Who: Called by the TaskNavigation graph for the TaskList destination.
 * 3. When: Executed at app launch or when returning from add/edit/stats screens.
 */
@Composable
fun TaskListScreen(
    uiState: TaskUiState,
    onAddTask: () -> Unit,
    onEditTask: (String) -> Unit,
    onOpenStats: () -> Unit,
    onDeleteTask: (String) -> Unit,
    onToggleTask: (String) -> Unit,
    onRemindTask: (Task) -> Unit,
) {
    var taskToDelete by remember { mutableStateOf<Task?>(null) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Task Manager",
                    style = MaterialTheme.typography.headlineMedium,
                )
                IconButton(onClick = onOpenStats) {
                    Icon(
                        painter = painterResource(R.drawable.ic_stats),
                        contentDescription = "Open task stats",
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTask) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "Add task",
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(scrollState),
        ) {
            if (uiState.tasks.isEmpty()) {
                Text(
                    text = "No tasks yet. Tap + to add one.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 24.dp),
                )
            }

            uiState.tasks.forEach { task ->
                TaskItem(
                    task = task,
                    onEditTask = onEditTask,
                    onAskDelete = { taskToDelete = task },
                    onToggleTask = onToggleTask,
                    onRemindTask = onRemindTask,
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }

    taskToDelete?.let { task ->
        TaskDeleteDialog(
            task = task,
            onDismiss = { taskToDelete = null },
            onDelete = {
                onDeleteTask(task.id)
                taskToDelete = null
            },
        )
    }
}

/**
 * 1. What: Displays one task row with completion, edit, delete, and reminder actions.
 * 2. Who: Called by TaskListScreen for each task in UI state.
 * 3. When: Executed whenever the task list screen recomposes with task data.
 */
@Composable
fun TaskItem(
    task: Task,
    onEditTask: (String) -> Unit,
    onAskDelete: () -> Unit,
    onToggleTask: (String) -> Unit,
    onRemindTask: (Task) -> Unit,
) {
    val textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null

    Card(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f),
            ),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onToggleTask(task.id) },
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    textDecoration = textDecoration,
                )
                Text(
                    text = task.description.ifBlank { "No description" },
                    style = MaterialTheme.typography.bodyMedium,
                    textDecoration = textDecoration,
                )
            }
            IconButton(onClick = { onEditTask(task.id) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = "Edit ${task.name}",
                    modifier = Modifier.size(24.dp),
                )
            }
            IconButton(onClick = onAskDelete) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Delete ${task.name}",
                    modifier = Modifier.size(24.dp),
                )
            }
            IconButton(onClick = { onRemindTask(task) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_reminder),
                    contentDescription = "Remind Me: ${task.name}",
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

/**
 * 1. What: Confirms deletion before removing a task from the list.
 * 2. Who: Called by TaskListScreen after the user taps a delete icon.
 * 3. When: Executed while a task is waiting for delete confirmation.
 */
@Composable
fun TaskDeleteDialog(
    task: Task,
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Task") },
        text = {
            Text("Are you sure you want to delete '${task.name}'? This action cannot be undone.")
        },
        confirmButton = {
            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
    )
}

/**
 * 1. What: Preview for one populated task item component.
 * 2. Who: Called by Android Studio Preview.
 * 3. When: Rendered during design-time UI inspection.
 */
@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    TaskItem(
        task =
            Task(
                name = "Finish CS4520 Assignment 5",
                description = "Task manager architecture",
            ),
        onEditTask = {},
        onAskDelete = {},
        onToggleTask = {},
        onRemindTask = {},
    )
}

/**
 * 1. What: Preview for the delete confirmation dialog component.
 * 2. Who: Called by Android Studio Preview.
 * 3. When: Rendered during design-time UI inspection.
 */
@Preview(showBackground = true)
@Composable
fun TaskDeleteDialogPreview() {
    TaskDeleteDialog(
        task = Task(name = "Complete Weekly Assignment 06"),
        onDismiss = {},
        onDelete = {},
    )
}

/**
 * 1. What: Preview for the populated task list screen.
 * 2. Who: Called by Android Studio Preview.
 * 3. When: Rendered during design-time UI inspection.
 */
@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen(
        uiState =
            TaskUiState(
                tasks =
                    listOf(
                        Task(
                            name = "Finish CS4520 Assignment 5",
                            description = "Architecture and state flow",
                            isCompleted = true,
                        ),
                        Task(
                            name = "Complete Weekly Assignment 06",
                            description = "By Next Tuesday",
                        ),
                    ),
            ),
        onAddTask = {},
        onEditTask = {},
        onOpenStats = {},
        onDeleteTask = {},
        onToggleTask = {},
        onRemindTask = {},
    )
}

/**
 * 1. What: Preview for the empty task list screen.
 * 2. Who: Called by Android Studio Preview.
 * 3. When: Rendered during design-time UI inspection.
 */
@Preview(showBackground = true)
@Composable
fun EmptyTaskListScreenPreview() {
    TaskListScreen(
        uiState = TaskUiState(),
        onAddTask = {},
        onEditTask = {},
        onOpenStats = {},
        onDeleteTask = {},
        onToggleTask = {},
        onRemindTask = {},
    )
}
