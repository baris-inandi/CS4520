package com.itsbaris.cs4520.a7.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement // 🔑 you'll need this for `description`

// ----- Search endpoint -----
@Serializable
data class SearchResponseDto(
    // ✍️ TODO: two fields mirroring the search JSON:
    //   • numFound : Int                 → give it a default of 0
    //   • docs     : List<BookDocDto>    → give it a default of emptyList()
)

@Serializable
data class BookDocDto(
    val key: String, // matches the JSON key "key" exactly — no annotation needed
    val title: String,
    // ✍️ TODO: the JSON key is "author_name" but we want the Kotlin name `authorName`.
    //       Bridge them with @SerialName("author_name"). It's a list and may be MISSING,
    //       so the type is  List<String>?  with a default of  null.
    // ✍️ TODO: "first_publish_year"  →  @SerialName + `firstPublishYear: Int? = null`
    // ✍️ TODO: "subject"             →  `subject: List<String>? = null`   (key already matches)
)

// ----- Works detail endpoint -----
@Serializable
data class WorkDetailDto(
    val title: String,
    // ✍️ TODO: "description" — the tricky one (can be a String OR an object).
    //       Declare it as:  val description: JsonElement? = null
    // ✍️ TODO: "subjects"            →  `subjects: List<String>? = null`
    // ✍️ TODO: "first_publish_date"  →  @SerialName + `firstPublishDate: String? = null`
    // ✍️ TODO: "covers"              →  `covers: List<Int>? = null`
)
