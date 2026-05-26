package com.itsbaris.cs4520.a5_inandioglu_6696.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a5_inandioglu_6696.R
import com.itsbaris.cs4520.a5_inandioglu_6696.model.Task
import com.itsbaris.cs4520.a5_inandioglu_6696.viewmodel.TaskUiState

/**
 * 1. What: Screen that displays task counts and network sync status.
 * 2. Who: Called by the TaskNavigation graph for the TaskStats destination.
 * 3. When: Executed when the user opens statistics from the task list.
 */
@Composable
fun TaskStatsScreen(
    uiState: TaskUiState,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = "Back",
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Task Stats",
                    style = MaterialTheme.typography.headlineMedium,
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Total Tasks:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = uiState.tasks.size.toString(),
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(34.dp))
            Text(
                text = "Completed Tasks:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = uiState.tasks.count { task -> task.isCompleted }.toString(),
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(44.dp))
            Text(
                text = "Network Sync Status:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = uiState.syncStatus,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

/**
 * 1. What: Preview for the task stats screen.
 * 2. Who: Called by Android Studio Preview.
 * 3. When: Rendered during design-time UI inspection.
 */
@Preview(showBackground = true)
@Composable
fun TaskStatsScreenPreview() {
    TaskStatsScreen(
        uiState =
            TaskUiState(
                tasks =
                    listOf(
                        Task(
                            name = "Finish CS4520 Assignment 5",
                            description = "Task manager architecture",
                            isCompleted = true,
                        ),
                        Task(
                            name = "Complete Weekly Assignment 06",
                            description = "By Next Tuesday",
                        ),
                    ),
                syncStatus = "Online / Sync Enabled",
            ),
        onBack = {},
    )
}
