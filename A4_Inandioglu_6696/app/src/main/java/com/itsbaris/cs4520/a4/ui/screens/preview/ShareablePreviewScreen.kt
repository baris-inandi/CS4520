package com.itsbaris.cs4520.a4.ui.screens.preview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itsbaris.cs4520.a4.model.Profile
import com.itsbaris.cs4520.a4.ui.common.Avatar
import com.itsbaris.cs4520.a4.ui.theme.A4_Inandioglu_6696Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareablePreviewScreen(
    profile: Profile,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shareable Preview") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
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
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column {
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.TopCenter,
                        ) {
                        }

                        Column(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, top = 56.dp, end = 20.dp, bottom = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Text(
                                text = profile.name.ifBlank { "No profile yet" },
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                            )
                            Text(
                                text = profile.bio.ifBlank { "No bio added" },
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                            )

                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(top = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                SharePill("Level ${profile.level}")
                                SharePill(profile.email.ifBlank { "No email added" })
                            }
                        }
                    }

                    Avatar(
                        profile = profile,
                        size = 88,
                        modifier =
                            Modifier
                                .align(Alignment.TopCenter)
                                .offset(y = 72.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun SharePill(text: String) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        shape = RoundedCornerShape(50),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShareablePreviewScreenPreview() {
    A4_Inandioglu_6696Theme {
        ShareablePreviewScreen(
            profile =
                Profile(
                    name = "Baris",
                    bio = "Mobile app development student",
                    email = "baris@example.com",
                    level = 19,
                    showOnline = true,
                ),
            onBack = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SharePillPreview() {
    A4_Inandioglu_6696Theme {
        SharePill("Level 19")
    }
}
