package com.itsbaris.cs4520.a4.ui.screens.customize

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.R
import com.itsbaris.cs4520.a4.model.Profile
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizeScreen(
    initial: Profile,
    onSave: (Profile) -> Unit,
    onCancel: () -> Unit,
) {
    var showOnline by rememberSaveable(initial.showOnline) { mutableStateOf(initial.showOnline) }
    var showResetDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Customize") },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
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
            FloatingActionButton(
                onClick = { onSave(initial.copy(showOnline = showOnline)) },
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Save customization",
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Show Online Status")
                Switch(
                    checked = showOnline,
                    onCheckedChange = { showOnline = it },
                )
            }

            TextButton(
                onClick = { showResetDialog = true },
                colors =
                    ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error,
                    ),
            ) {
                Text("Reset to defaults")
            }
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Reset appearance?") },
            text = { Text("This will clear your online-status settings.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showOnline = Profile().showOnline
                        showResetDialog = false
                    },
                ) {
                    Text("Reset")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Cancel")
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomizeScreenPreview() {
    A4_Inandioglu_6696Theme {
        CustomizeScreen(
            initial =
                Profile(
                    name = "Baris",
                    bio = "Mobile app development student",
                    email = "baris@example.com",
                    level = 19,
                    showOnline = true,
                ),
            onSave = {},
            onCancel = {},
        )
    }
}
