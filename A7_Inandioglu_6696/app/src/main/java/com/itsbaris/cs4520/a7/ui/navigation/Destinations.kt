package com.itsbaris.cs4520.a7.ui.navigation

import kotlinx.serialization.Serializable

@Serializable object BookSearch // the search screen (no args)

@Serializable data class BookDetail(
    val workKey: String,
) // the detail screen (carries the book key)
