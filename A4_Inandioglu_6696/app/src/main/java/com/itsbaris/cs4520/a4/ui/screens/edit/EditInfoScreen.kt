package com.itsbaris.cs4520.a4.ui.screens.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun EditInfoScreen(
    initial: Profile,
    onSave: (Profile) -> Unit,
    onCancel: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Edit Info")
        Button(onClick = { onSave(initial) }) {
            Text("Save")
        }
        OutlinedButton(onClick = onCancel) {
            Text("Cancel")
        }
    }
}
