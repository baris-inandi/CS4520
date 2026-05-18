package com.itsbaris.cs4520.a4.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncompleteHomeScreen(onEditInfo: () -> Unit) {
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
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text("Tell us about yourself.")
                Button(onClick = onEditInfo) {
                    Text("Get Started")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IncompleteHomeScreenPreview() {
    A4_Inandioglu_6696Theme {
        IncompleteHomeScreen(onEditInfo = {})
    }
}
