package com.example.watchlist.api.apiResponse

data class AnimeResponse(
    val data: List<Anime>,
    val pagination: Pagination
)