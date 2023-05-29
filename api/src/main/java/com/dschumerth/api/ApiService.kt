package com.dschumerth.api

import com.dschumerth.api.response.ArtworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("artworks")
    suspend fun getArtworks(
        @Query("page") page: Int?,
        @Query("limit") limit: Int? = 20
    ): ArtworkResponse
}
