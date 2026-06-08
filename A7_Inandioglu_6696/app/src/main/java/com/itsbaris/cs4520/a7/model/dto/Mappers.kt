package com.itsbaris.cs4520.a7.model.dto

import com.itsbaris.cs4520.a7.model.Book
import com.itsbaris.cs4520.a7.model.BookDetail
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

/**
 * 1. What: Converts one search result DTO into the app's Book model.
 * 2. Who:  Called by BookRepository after search JSON is parsed.
 * 3. When: Used for each item returned by the search endpoint.
 */
fun BookDocDto.toDomain(): Book =
    Book(
        key = key,
        title = title,
        author = authorName?.firstOrNull() ?: "Unknown Author",
        year = firstPublishYear ?: 0,
        subjects = subject ?: emptyList(),
    )

/**
 * 1. What: Converts a work detail DTO into the app's BookDetail model.
 * 2. Who:  Called by BookRepository after detail JSON is parsed.
 * 3. When: Used when a selected book's detail screen loads.
 */
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
