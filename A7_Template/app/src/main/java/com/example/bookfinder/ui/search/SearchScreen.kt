package com.example.bookfinder.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookfinder.ui.common.BookCard
import com.example.bookfinder.ui.common.BookSearchBar
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    onBookClick: (String) -> Unit,
    viewModel: SearchViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // ✍️ TODO — validation. Decide whether to actually search:
    //   • If query.trim() is shorter than 2 characters → show a Snackbar
    //       "Please enter at least 2 characters." and DO NOT call the network.
    //       HINT: scope.launch { snackbarHostState.showSnackbar("Please enter at least 2 characters.") }
    //   • Otherwise → viewModel.search(query.trim())
    fun submit() {
        // TODO: implement the two cases above
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(Modifier.padding(padding).fillMaxSize()) {
            BookSearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = { submit() },
                modifier = Modifier.padding(16.dp),
            )

            when (val s = state) {
                is SearchUiState.Idle -> {
                    CenteredMessage("Search for a book to get started")
                }

                is SearchUiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is SearchUiState.Error -> {
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(s.message)
                        Button(onClick = { viewModel.retry() }) { Text("Retry") }
                    }
                }

                is SearchUiState.Success -> {
                    // ✍️ TODO — empty results: if s.books.isEmpty(), show
                    //   CenteredMessage("No books found for \"$query\".") instead of the list.
                    //   (CenteredMessage helper is provided at the bottom of this file.)
                    LazyColumn {
                        items(s.books, key = { it.key }) { book ->
                            // ✍️ TODO: show a BookCard for `book`.
                            //          On click, call onBookClick(book.key).
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CenteredMessage(text: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(text) }
}
