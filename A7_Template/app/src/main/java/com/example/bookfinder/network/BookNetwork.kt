package com.example.bookfinder.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object BookNetwork {
    // ignoreUnknownKeys = true is REQUIRED — the API returns dozens of fields
    // we don't model. Without this, the app crashes on every call.
    private val json = Json { ignoreUnknownKeys = true }

    val api: OpenLibraryApi =
        Retrofit
            .Builder()
            .baseUrl(OpenLibraryApi.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(OpenLibraryApi::class.java)
}
