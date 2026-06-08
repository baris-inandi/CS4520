package com.itsbaris.cs4520.a7.data

import com.itsbaris.cs4520.a7.model.Book
import com.itsbaris.cs4520.a7.model.BookDetail
import com.itsbaris.cs4520.a7.model.dto.toDomain
import com.itsbaris.cs4520.a7.network.BookNetwork
import com.itsbaris.cs4520.a7.network.OpenLibraryApi

class BookRepository(
    private val api: OpenLibraryApi = BookNetwork.api,
) {
    /**
     * 1. What: Searches Open Library for books matching the query.
     * 2. Who:  Called by SearchViewModel.
     * 3. When: Triggered after the user submits a valid search.
     */
    suspend fun searchBooks(query: String): List<Book> {
        if (query.length < 2) return emptyList()
        return api.searchBooks(query).docs.map { it.toDomain() }
    }

    /**
     * 1. What: Loads detail data for one Open Library work key.
     * 2. Who:  Called by BookDetailViewModel.
     * 3. When: Triggered when the detail screen opens or retries.
     */
    suspend fun getBookDetail(workKey: String): BookDetail {
        // workKey arrives as "/works/OL82563W" but the endpoint wants just "OL82563W"
        val key = workKey.removePrefix("/works/")
        return api.getWorkDetail(key).toDomain()
    }
}
