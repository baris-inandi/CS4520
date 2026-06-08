package com.itsbaris.cs4520.a7.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(
    workKey: String,
    onBack: () -> Unit,
    viewModel: BookDetailViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // Kick off the network call once, when the screen first appears.
    LaunchedEffect(workKey) { viewModel.loadBookDetail(workKey) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book Details") },
                navigationIcon = { IconButton(onClick = onBack) { Text("←") } },
            )
        },
    ) { padding ->
        Box(Modifier.padding(padding).fillMaxSize()) {
            when (val s = state) {
                is DetailUiState.Loading -> {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }

                is DetailUiState.Error -> {
                    Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(s.message)
                        Button(onClick = { viewModel.loadBookDetail(workKey) }) { Text("Retry") }
                    }
                }

                is DetailUiState.Success -> {
                    val detail = s.detail
                    Column(Modifier.padding(16.dp)) {
                        // ✍️ TODO: show the book's details:
                        //   - Title (large + bold)
                        //   - Description, or "No description available" if blank
                        //   - First publish date, or "Unknown" if null
                        //   - Subjects (comma-separated is fine)
                        //   - (Optional ⭐ Bonus) the cover image with Coil — see the assignment
                    }
                }
            }
        }
    }
}
