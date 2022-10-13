package com.example.watchlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.watchlist.api.Converters
import com.example.watchlist.api.apiResponse.Anime

@Database(entities = [Anime::class], version = 1)
@TypeConverters(Converters::class)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun getAnimDao() : AnimeDao

    companion object {
        @Volatile
        private var INSTANCE : AnimeDatabase? = null

        fun getInstance(context: Context) : AnimeDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnimeDatabase::class.java,
                        "anime_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}