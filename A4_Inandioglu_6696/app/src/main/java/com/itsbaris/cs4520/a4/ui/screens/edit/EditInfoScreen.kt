package com.itsbaris.cs4520.a4.ui.screens.edit

import android.util.Patterns
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.model.Profile
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditInfoScreen(
    initial: Profile,
    onSave: (Profile) -> Unit,
    onCancel: () -> Unit,
) {
    var name by remember(initial) { mutableStateOf(initial.name) }
    var bio by remember(initial) { mutableStateOf(initial.bio) }
    var email by remember(initial) { mutableStateOf(initial.email) }
    var levelText by remember(initial) { mutableStateOf(initial.level.toString()) }
    var showDiscardDialog by remember { mutableStateOf(false) }

    val parsedLevel = levelText.toIntOrNull()
    val isNameError = name.isBlank()
    val isEmailError = email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isLevelError = parsedLevel == null || parsedLevel !in 1..100
    val isValid = !isNameError && !isEmailError && !isLevelError
    val hasUnsavedChanges =
        name != initial.name ||
            bio != initial.bio ||
            email != initial.email ||
            levelText != initial.level.toString()

    fun requestCancel() {
        if (hasUnsavedChanges) {
            showDiscardDialog = true
        } else {
            onCancel()
        }
    }

    BackHandler(enabled = hasUnsavedChanges) {
        showDiscardDialog = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Info") },
                navigationIcon = {
                    IconButton(onClick = { requestCancel() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
            )
        },
        floatingActionButton = {
            if (isValid) {
                FloatingActionButton(
                    onClick = {
                        onSave(
                            initial.copy(
                                name = name.trim(),
                                bio = bio,
                                email = email.trim(),
                                level = parsedLevel ?: initial.level,
                            ),
                        )
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Save profile",
                    )
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                isError = isNameError,
                supportingText = {
                    if (isNameError) {
                        Text("Name cannot be empty")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = bio,
                onValueChange = { bio = it },
                label = { Text("Bio") },
                minLines = 3,
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                isError = isEmailError,
                supportingText = {
                    if (isEmailError) {
                        Text("Enter a valid email address")
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = levelText,
                onValueChange = { levelText = it },
                label = { Text("Level (1-100)") },
                isError = isLevelError,
                supportingText = {
                    if (isLevelError) {
                        Text("Level must be between 1 and 100")
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }

    if (showDiscardDialog) {
        AlertDialog(
            onDismissRequest = { showDiscardDialog = false },
            title = { Text("Discard changes?") },
            text = { Text("You haven't saved your edits.") },
            confirmButton = {
                TextButton(onClick = onCancel) {
                    Text("Discard")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDiscardDialog = false }) {
                    Text("Keep editing")
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EditInfoScreenPreview() {
    A4_Inandioglu_6696Theme {
        EditInfoScreen(
            initial =
                Profile(
                    name = "Baris",
                    bio = "Mobile app development student",
                    email = "baris@example.com",
                    level = 19,
                ),
            onSave = {},
            onCancel = {},
        )
    }
}
