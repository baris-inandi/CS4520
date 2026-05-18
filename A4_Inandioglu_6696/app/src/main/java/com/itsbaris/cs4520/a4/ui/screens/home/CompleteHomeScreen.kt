package com.itsbaris.cs4520.a4.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.model.Profile
import com.itsbaris.cs4520.a4.ui.commons.Avatar
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteHomeScreen(
    profile: Profile,
    onEditInfo: () -> Unit,
    onCustomize: () -> Unit,
    onCompactPreview: () -> Unit,
    onFullPreview: () -> Unit,
    onShareablePreview: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Profile") },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onEditInfo) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit profile",
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Avatar(profile = profile, size = 112)

            Text(
                text = profile.name,
                style = MaterialTheme.typography.headlineMedium,
            )

            if (profile.bio.isNotBlank()) {
                Text(
                    text = profile.bio,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = onCustomize) {
                Text("Customize")
            }

            Text(
                text = "Previews",
                style = MaterialTheme.typography.titleMedium,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedButton(
                    onClick = onCompactPreview,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Compact")
                }
                OutlinedButton(
                    onClick = onFullPreview,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Full")
                }
                OutlinedButton(
                    onClick = onShareablePreview,
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Share")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CompleteHomeScreenPreview() {
    A4_Inandioglu_6696Theme {
        CompleteHomeScreen(
            profile =
                Profile(
                    name = "Baris",
                    bio = "Mobile app development student",
                    email = "baris@example.com",
                    level = 19,
                ),
            onEditInfo = {},
            onCustomize = {},
            onCompactPreview = {},
            onFullPreview = {},
            onShareablePreview = {},
        )
    }
}
