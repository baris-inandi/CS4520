package com.itsbaris.cs4520.a5.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a5.R
import com.itsbaris.cs4520.a5.model.Task

/**
 * 1. What: Screen that lets the user add a new task or edit an existing task.
 * 2. Who: Called by the TaskNavigation graph for the AddEditTask destination.
 * 3. When: Executed when the user taps add or edit from the task list.
 */
@Composable
fun AddEditTaskScreen(
    task: Task?,
    onBack: () -> Unit,
    onSave: (String, String) -> Unit,
) {
    var name by rememberSaveable(task?.id) { mutableStateOf(task?.name.orEmpty()) }
    var description by rememberSaveable(task?.id) { mutableStateOf(task?.description.orEmpty()) }

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
                    text = if (task == null) "Add Task" else "Edit Task",
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
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Task Name") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Task Description (Optional)") },
                minLines = 2,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = { onSave(name, description) },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(52.dp),
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_save),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Save Task",
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

/**
 * 1. What: Preview for the add task form screen.
 * 2. Who: Called by Android Studio Preview.
 * 3. When: Rendered during design-time UI inspection.
 */
@Preview(showBackground = true)
@Composable
fun AddEditTaskScreenPreview() {
    AddEditTaskScreen(
        task = null,
        onBack = {},
        onSave = { _, _ -> },
    )
}

/**
 * 1. What: Preview for the edit task form screen with pre-filled values.
 * 2. Who: Called by Android Studio Preview.
 * 3. When: Rendered during design-time UI inspection.
 */
@Preview(showBackground = true)
@Composable
fun EditTaskScreenPreview() {
    AddEditTaskScreen(
        task =
            Task(
                name = "Complete Weekly Assignment 06",
                description = "By Next Tuesday",
            ),
        onBack = {},
        onSave = { _, _ -> },
    )
}
