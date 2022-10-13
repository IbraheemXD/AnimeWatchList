package com.example.watchlist.repository

import com.example.watchlist.api.RetrofitInstance
import com.example.watchlist.db.AnimeDatabase

class AnimeRepo(
    val db: AnimeDatabase
) {

    suspend fun getAnimeList(page: Int, limit: Int) =
        RetrofitInstance.api.getAnimeList(page, limit)

    suspend fun queryAnime(query: String) =
        RetrofitInstance.api.queryAnime(query)
}