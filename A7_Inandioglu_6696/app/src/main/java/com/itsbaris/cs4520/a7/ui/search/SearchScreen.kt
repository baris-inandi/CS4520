package com.itsbaris.cs4520.a7.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itsbaris.cs4520.a7.model.Book
import com.itsbaris.cs4520.a7.ui.common.BookCard
import com.itsbaris.cs4520.a7.ui.common.BookSearchBar
import com.itsbaris.cs4520.a7.ui.theme.A7_Inandioglu_6696Theme
import kotlinx.coroutines.launch

/**
 * 1. What: Shows the search field, search state, and book results.
 * 2. Who:  Called by AppNavigation for the start destination.
 * 3. When: Rendered when the app is on the book search screen.
 */
@Composable
fun SearchScreen(
    onBookClick: (String) -> Unit,
    viewModel: SearchViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }
    var submittedQuery by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    /**
     * 1. What: Validates the query and starts a search when it is long enough.
     * 2. Who:  Called by BookSearchBar.
     * 3. When: Triggered by the keyboard Search action.
     */
    fun submit() {
        val trimmedQuery = query.trim()
        if (trimmedQuery.length < 2) {
            scope.launch {
                snackbarHostState.showSnackbar("Please enter at least 2 characters.")
            }
            return
        }
        submittedQuery = trimmedQuery
        viewModel.search(trimmedQuery)
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
                    if (s.books.isEmpty()) {
                        CenteredMessage("No books found for '$submittedQuery'.")
                    } else {
                        SearchResultsList(
                            books = s.books,
                            onBookClick = onBookClick,
                        )
                    }
                }
            }
        }
    }
}

/**
 * 1. What: Shows the list of clickable book search results.
 * 2. Who:  Called by SearchScreen after a successful non-empty search.
 * 3. When: Rendered when Open Library returns one or more books.
 */
@Composable
private fun SearchResultsList(
    books: List<Book>,
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(books, key = { it.key }) { book ->
            BookCard(
                book = book,
                onClick = { onBookClick(book.key) },
            )
        }
    }
}

/**
 * 1. What: Shows a centered text message for empty screen states.
 * 2. Who:  Called by SearchScreen.
 * 3. When: Rendered for idle searches and empty search results.
 */
@Composable
private fun CenteredMessage(text: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(text) }
}

/**
 * 1. What: Previews the search screen's initial idle state.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing the main search screen layout.
 */
@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    A7_Inandioglu_6696Theme {
        SearchScreen(
            onBookClick = {},
            viewModel = SearchViewModel(),
        )
    }
}

/**
 * 1. What: Previews the reusable search results list.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing populated search results.
 */
@Preview(showBackground = true)
@Composable
private fun SearchResultsListPreview() {
    A7_Inandioglu_6696Theme {
        SearchResultsList(
            books =
                listOf(
                    Book(
                        key = "/works/OL82563W",
                        title = "Harry Potter and the Philosopher's Stone",
                        author = "J.K. Rowling",
                        year = 1997,
                        subjects = listOf("Fantasy", "Magic"),
                    ),
                    Book(
                        key = "/works/OL27448W",
                        title = "The Hobbit",
                        author = "J.R.R. Tolkien",
                        year = 1937,
                        subjects = listOf("Fantasy", "Adventure"),
                    ),
                ),
            onBookClick = {},
        )
    }
}

/**
 * 1. What: Previews a centered empty-state message.
 * 2. Who:  Used by Android Studio's preview panel.
 * 3. When: Checked while reviewing idle and no-results states.
 */
@Preview(showBackground = true)
@Composable
private fun CenteredMessagePreview() {
    A7_Inandioglu_6696Theme {
        CenteredMessage("Search for a book to get started")
    }
}
