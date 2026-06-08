package com.example.bookfinder.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookfinder.data.BookRepository
import com.example.bookfinder.model.BookDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailUiState {
    data object Loading : DetailUiState()

    data class Success(
        val detail: BookDetail,
    ) : DetailUiState()

    data class Error(
        val message: String,
    ) : DetailUiState()
}

class BookDetailViewModel(
    private val repo: BookRepository = BookRepository(),
) : ViewModel() {
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadBookDetail(workKey: String) {
        _uiState.value = DetailUiState.Loading
        viewModelScope.launch {
            // ✍️ TODO: mirror SearchViewModel.search() — wrap in:
            //   _uiState.value = try { DetailUiState.Success(repo.getBookDetail(workKey)) }
            //   catch (e: IOException)   { DetailUiState.Error("No internet connection. Check your network and try again.") }
            //   catch (e: HttpException) { DetailUiState.Error("Server error: ${e.code()}") }
            //   catch (e: Exception)     { DetailUiState.Error("Something went wrong.") }
        }
    }
}
