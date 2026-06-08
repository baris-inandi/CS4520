package com.itsbaris.cs4520.a5.navigation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.itsbaris.cs4520.a5.model.Task
import com.itsbaris.cs4520.a5.notifications.TaskNotificationHelper
import com.itsbaris.cs4520.a5.screens.AddEditTaskScreen
import com.itsbaris.cs4520.a5.screens.TaskListScreen
import com.itsbaris.cs4520.a5.screens.TaskStatsScreen
import com.itsbaris.cs4520.a5.ui.theme.A5_Inandioglu_6696Theme
import com.itsbaris.cs4520.a5.viewmodel.TaskEvent
import com.itsbaris.cs4520.a5.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

/**
 * 1. What: Defines the type-safe navigation graph for the task manager app.
 * 2. Who: Called by MainActivity inside the app theme.
 * 3. When: Executed when the activity sets Compose content.
 */
@Composable
fun TaskNavigation(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current
    val coroutineScope = rememberCoroutineScope()
    var pendingReminderTask by remember { mutableStateOf<Task?>(null) }
    val permissionLauncher =
        if (!isPreview) {
            rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                val task = pendingReminderTask
                pendingReminderTask = null
                if (isGranted && task != null) {
                    TaskNotificationHelper.showTaskReminder(context, task)
                } else if (!isGranted) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Notification permission denied")
                    }
                }
            }
        } else {
            null
        }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is TaskEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->
        val onRemindTask: (Task) -> Unit = { task ->
            if (isPreview) {
                Unit
            } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                TaskNotificationHelper.showTaskReminder(context, task)
            } else if (
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                TaskNotificationHelper.showTaskReminder(context, task)
            } else {
                pendingReminderTask = task
                permissionLauncher?.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        NavHost(
            navController = navController,
            startDestination = TaskList,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable<TaskList> {
                TaskListScreen(
                    uiState = uiState.value,
                    onAddTask = { navController.navigate(AddEditTask()) },
                    onEditTask = { taskId -> navController.navigate(AddEditTask(taskId)) },
                    onOpenStats = { navController.navigate(TaskStats) },
                    onDeleteTask = viewModel::deleteTask,
                    onToggleTask = viewModel::toggleTaskCompleted,
                    onRemindTask = onRemindTask,
                )
            }

            composable<AddEditTask> { backStackEntry ->
                val route = backStackEntry.toRoute<AddEditTask>()
                AddEditTaskScreen(
                    task = viewModel.getTask(route.taskId),
                    onBack = { navController.popBackStack() },
                    onSave = { name, description ->
                        val didSave =
                            if (route.taskId == null) {
                                viewModel.addTask(name, description)
                            } else {
                                viewModel.updateTask(route.taskId, name, description)
                            }
                        if (didSave) {
                            navController.popBackStack()
                        }
                    },
                )
            }

            composable<TaskStats> {
                TaskStatsScreen(
                    uiState = uiState.value,
                    onBack = { navController.popBackStack() },
                )
            }
        }
    }
}

/**
 * 1. What: Preview for the task navigation graph starting on the task list.
 * 2. Who: Called by Android Studio Preview.
 * 3. When: Rendered during design-time UI inspection.
 */
@Preview(showBackground = true)
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun TaskNavigationPreview() {
    A5_Inandioglu_6696Theme {
        TaskNavigation(viewModel = TaskViewModel())
    }
}
