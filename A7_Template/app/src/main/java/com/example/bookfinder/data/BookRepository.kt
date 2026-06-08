package com.example.bookfinder.data

import com.example.bookfinder.model.Book
import com.example.bookfinder.model.BookDetail
import com.example.bookfinder.model.dto.toDomain
import com.example.bookfinder.network.BookNetwork
import com.example.bookfinder.network.OpenLibraryApi

class BookRepository(
    private val api: OpenLibraryApi = BookNetwork.api,
) {
    suspend fun searchBooks(query: String): List<Book> {
        if (query.length < 2) return emptyList()
        return api.searchBooks(query).docs.map { it.toDomain() }
    }

    suspend fun getBookDetail(workKey: String): BookDetail {
        // workKey arrives as "/works/OL82563W" but the endpoint wants just "OL82563W"
        val key = workKey.removePrefix("/works/")
        return api.getWorkDetail(key).toDomain()
    }
}
