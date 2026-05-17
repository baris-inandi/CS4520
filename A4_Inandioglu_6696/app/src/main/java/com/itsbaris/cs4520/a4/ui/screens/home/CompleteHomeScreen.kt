package com.itsbaris.cs4520.a4.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.model.Profile

@Composable
fun CompleteHomeScreen(
    profile: Profile,
    onEditInfo: () -> Unit,
    onCustomize: () -> Unit,
    onCompactPreview: () -> Unit,
    onFullPreview: () -> Unit,
    onShareablePreview: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(profile.name)
        Text(profile.bio)

        Button(onClick = onCustomize) {
            Text("Customize")
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = onCompactPreview) {
                Text("Compact")
            }
            OutlinedButton(onClick = onFullPreview) {
                Text("Full")
            }
            OutlinedButton(onClick = onShareablePreview) {
                Text("Share")
            }
        }

        Button(onClick = onEditInfo) {
            Text("Edit Info")
        }
    }
}
