package com.itsbaris.cs4520.a7.model

// Full detail (the second screen)
data class BookDetail(
    val title: String,
    val description: String, // safely pulled from String OR object — see Mappers.kt
    val subjects: List<String>,
    val firstPublishDate: String?,
    val coverId: Int?, // for the cover image URL
)
