package com.itsbaris.cs4520.a7.model.dto

import com.itsbaris.cs4520.a7.model.Book
import com.itsbaris.cs4520.a7.model.BookDetail
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

// Search DTO → domain. Handles all the nullable fields safely.
fun BookDocDto.toDomain(): Book =
    Book(
        key = key,
        title = title,
        author = authorName?.firstOrNull() ?: "Unknown Author",
        year = firstPublishYear ?: 0,
        subjects = subject ?: emptyList(),
    )

// Detail DTO → domain. Safely handles description being a String, an object, or missing.
fun WorkDetailDto.toDomain(): BookDetail {
    val descriptionText: String =
        when (val d = description) {
            is JsonPrimitive -> d.contentOrNull ?: ""

            // "description": "text..."
            is JsonObject -> (d["value"] as? JsonPrimitive)?.contentOrNull ?: ""

            // "description": { "value": "text..." }
            else -> "" // missing / null
        }
    return BookDetail(
        title = title,
        description = descriptionText,
        subjects = subjects ?: emptyList(),
        firstPublishDate = firstPublishDate,
        coverId = covers?.firstOrNull(),
    )
}
