package com.itsbaris.cs4520.a7.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SearchResponseDto(
    val numFound: Int = 0,
    val docs: List<BookDocDto> = emptyList(),
)

@Serializable
data class BookDocDto(
    val key: String,
    val title: String,
    @SerialName("author_name") val authorName: List<String>? = null,
    @SerialName("first_publish_year") val firstPublishYear: Int? = null,
    val subject: List<String>? = null,
)

@Serializable
data class WorkDetailDto(
    val title: String,
    val description: JsonElement? = null,
    val subjects: List<String>? = null,
    @SerialName("first_publish_date") val firstPublishDate: String? = null,
    val covers: List<Int>? = null,
)
