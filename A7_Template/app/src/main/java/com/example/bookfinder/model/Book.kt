package com.example.bookfinder.model

// Search result (one row in the list)
data class Book(
    val key: String, // unique id, e.g. "/works/OL82563W"
    val title: String,
    val author: String, // first author, or "Unknown Author"
    val year: Int, // first publish year, or 0
    val subjects: List<String>,
)
