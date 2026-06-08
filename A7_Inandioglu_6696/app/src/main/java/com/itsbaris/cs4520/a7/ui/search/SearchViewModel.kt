package com.itsbaris.cs4520.a7.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itsbaris.cs4520.a7.data.BookRepository
import com.itsbaris.cs4520.a7.model.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// The 4 states the search screen can be in.
sealed class SearchUiState {
    data object Idle : SearchUiState() // before any search

    data object Loading : SearchUiState() // request in flight

    data class Success(
        val books: List<Book>,
    ) : SearchUiState() // got results (may be empty!)

    data class Error(
        val message: String,
    ) : SearchUiState() // something failed
}

class SearchViewModel(
    private val repo: BookRepository = BookRepository(),
) : ViewModel() {
    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var lastQuery = ""

    /**
     * 1. What: Searches the Open Library API for books matching the query.
     * 2. Who:  Called by SearchScreen when the user submits a search.
     * 3. When: Triggered on the keyboard "Search" action or the search icon tap.
     */
    fun search(query: String) {
        lastQuery = query
        _uiState.value = SearchUiState.Loading
        viewModelScope.launch {
            _uiState.value =
                try {
                    SearchUiState.Success(repo.searchBooks(query))
                } catch (e: IOException) {
                    SearchUiState.Error("No internet connection. Check your network and try again.")
                } catch (e: HttpException) {
                    SearchUiState.Error("Server error: ${e.code()}")
                } catch (e: Exception) {
                    SearchUiState.Error("Something went wrong.")
                }
        }
    }

    /**
     * 1. What: Re-runs the most recent valid search query.
     * 2. Who:  Called by SearchScreen's Retry button.
     * 3. When: Triggered after a search request fails.
     */
    fun retry() {
        if (lastQuery.isNotBlank()) search(lastQuery)
    }
}
