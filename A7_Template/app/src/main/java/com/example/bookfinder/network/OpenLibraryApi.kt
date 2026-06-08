package com.example.bookfinder.network

import com.example.bookfinder.model.dto.SearchResponseDto
import com.example.bookfinder.model.dto.WorkDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 15,
    ): SearchResponseDto

    @GET("works/{key}.json")
    suspend fun getWorkDetail(
        @Path("key") key: String, // just the id, e.g. "OL82563W"
    ): WorkDetailDto

    companion object {
        const val BASE_URL = "https://openlibrary.org/"
    }
}
