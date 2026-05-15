package com.itsbaris.cs4520.a3.ui.profiledashboard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a3.ui.theme.A3_Inandioglu_6696Theme

@Composable
fun ProfileDashboard() {
    var username by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var level by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surfaceDim,
                    ),
                ),
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        UserInfoSection(
            username = username,
            bio = bio,
            onUsernameChange = { if (it.length <= 15) username = it },
            onBioChange = { if (it.length <= 100) bio = it },
        )
        UserSettingsSection(
            notificationsEnabled = notificationsEnabled,
            onNotificationsChange = { notificationsEnabled = it },
            level = level,
            onDecreaseLevel = { level-- },
            onIncreaseLevel = { level++ },
        )
        SaveProfileButton(
            enabled = username.isNotBlank(),
            onClick = {
                Log.i(
                    "Save",
                    "Username=$username, Bio=$bio, Notifications=$notificationsEnabled, Level=$level",
                )
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDashboardPreview() {
    A3_Inandioglu_6696Theme {
        ProfileDashboard()
    }
}
