package com.itsbaris.cs4520.a4.ui.screens.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.model.Profile

@Composable
fun ShareablePreviewScreen(
    profile: Profile,
    onBack: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Shareable Preview")
                Text(profile.name.ifBlank { "No profile yet" })
                Text(profile.bio)
            }
        }
        OutlinedButton(onClick = onBack) {
            Text("Back")
        }
    }
}
