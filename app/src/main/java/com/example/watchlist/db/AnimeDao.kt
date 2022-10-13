package com.example.watchlist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.watchlist.api.apiResponse.Anime

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: Anime)

    @Delete
    suspend fun delete(anime: Anime)

    @Query("SELECT * FROM `anime-table`")
    fun fetchAllAnime() : LiveData<List<Anime>>
}