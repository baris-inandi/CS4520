package com.itsbaris.cs4520.a7.network

import com.itsbaris.cs4520.a7.model.dto.SearchResponseDto
import com.itsbaris.cs4520.a7.model.dto.WorkDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenLibraryApi {
    /**
     * 1. What: Requests search results from Open Library.
     * 2. Who:  Called by BookRepository.
     * 3. When: Triggered after a valid search query is submitted.
     */
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 15,
    ): SearchResponseDto

    /**
     * 1. What: Requests detail data for one Open Library work.
     * 2. Who:  Called by BookRepository.
     * 3. When: Triggered after a search result is selected.
     */
    @GET("works/{key}.json")
    suspend fun getWorkDetail(
        @Path("key") key: String, // just the id, e.g. "OL82563W"
    ): WorkDetailDto

    companion object {
        const val BASE_URL = "https://openlibrary.org/"
    }
}
