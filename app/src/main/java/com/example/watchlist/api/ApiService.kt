package com.example.watchlist.api

import com.example.watchlist.api.apiResponse.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v4/anime")
    suspend fun getAnimeList(
        @Query("page")
        page : Int = 1,
        @Query("limit")
        limit : Int = 10
    ) : Response<AnimeResponse>

    @GET("v4/anime")
    suspend fun queryAnime(
        @Query("q")
        q : String
    ) : Response<AnimeResponse>

}