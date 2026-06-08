package com.itsbaris.cs4520.a7.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itsbaris.cs4520.a7.model.BookDetail
import com.itsbaris.cs4520.a7.ui.theme.A7_Inandioglu_6696Theme

/**
 * 1. What: Shows loading, error, or detail content for one selected book.
 * 2. Who:  Called by AppNavigation after a search result is tapped.
 * 3. When: Rendered on the detail route with a selected work key.
 */
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
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
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
                    BookDetailContent(detail = s.detail)
                }
            }
        }
    }
}

/**
 * 1. What: Shows the loaded book title, cover, description, date, and subjects.
 * 2. Who:  Called by BookDetailScreen after detail data loads.
 * 3. When: Rendered for the detail screen success state.
 */
@Composable
private fun BookDetailContent(
    detail: BookDetail,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
    ) {
        if (detail.coverId != null) {
            BookCoverImage(
                title = detail.title,
                coverId = detail.coverId,
            )
            Spacer(Modifier.height(24.dp))
        }

        Text(
            text = detail.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = detail.description.ifBlank { "No description available" },
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "First publish date: ${detail.firstPublishDate?.takeIf { it.isNotBlank() } ?: "Unknown"}",
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Subjects: ${detail.subjects.takeIf { it.isNotEmpty() }?.joinToString() ?: "Unknown"}",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

/**
 * 1. What: Previews populated book detail content.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing the detail success state.
 */
@Preview(showBackground = true)
@Composable
private fun BookDetailContentPreview() {
    A7_Inandioglu_6696Theme {
        BookDetailContent(
            detail =
                BookDetail(
                    title = "Harry Potter and the Philosopher's Stone",
                    description = "A young wizard discovers magic, friendship, and danger at Hogwarts.",
                    subjects = listOf("Fantasy", "Magic", "Schools"),
                    firstPublishDate = "June 26, 1997",
                    coverId = 10521270,
                ),
        )
    }
}
