package com.example.watchlist.api

import androidx.room.TypeConverter
import com.example.watchlist.api.apiResponse.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun airedToGson(aired: Aired) : String {
        return  Gson().toJson(aired)
    }

    @TypeConverter
    fun gsonToAired(json: String) : Aired {
        return Gson().fromJson(json, Aired::class.java)
    }

    @TypeConverter
    fun broadcastToGson(broadcast: Broadcast) : String {
        return  Gson().toJson(broadcast)
    }

    @TypeConverter
    fun gsonToBroadcast(json: String) : Broadcast {
        return Gson().fromJson(json, Broadcast::class.java)
    }

    @TypeConverter
    fun demographicToGson(demographic: List<Demographic>) : String {
        return  Gson().toJson(demographic)
    }

    @TypeConverter
    fun gsonToDemographic(json: String) : List<Demographic> {
        val listType = object : TypeToken<List<Demographic>>(){}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun explicitGenresToGson(explicit_genres: List<Any>) : String {
        return  Gson().toJson(explicit_genres)
    }

    @TypeConverter
    fun gsonToExplicitGenres(json: String) : List<Any> {
        val listType = object : TypeToken<List<Any>>(){}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun genreToGson(genres: List<Genre>) : String {
        return  Gson().toJson(genres)
    }

    @TypeConverter
    fun gsonToGenre(json: String) : List<Genre> {
        val listType = object : TypeToken<List<Genre>>(){}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun imagesToGson(images: Images) : String {
        return  Gson().toJson(images)
    }

    @TypeConverter
    fun gsonToImages(json: String) : Images {
        return Gson().fromJson(json, Images::class.java)
    }

    @TypeConverter
    fun licensorToGson(licensors: List<Licensor>) : String {
        return  Gson().toJson(licensors)
    }

    @TypeConverter
    fun gsonToLicensor(json: String) : List<Licensor> {
        val listType = object : TypeToken<List<Licensor>>(){}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun producerToGson(producers: List<Producer>) : String {
        return  Gson().toJson(producers)
    }

    @TypeConverter
    fun gsonToProducer(json: String) : List<Producer> {
        val listType = object : TypeToken<List<Producer>>(){}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun studioToGson(studios: List<Studio>) : String {
        return  Gson().toJson(studios)
    }

    @TypeConverter
    fun gsonToStudio(json: String) : List<Studio> {
        val listType = object : TypeToken<List<Studio>>(){}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun trailerToGson(trailer: Trailer) : String {
        return  Gson().toJson(trailer)
    }

    @TypeConverter
    fun gsonToTrailer(json: String) : Trailer {
        return Gson().fromJson(json, Trailer::class.java)
    }

    @TypeConverter
    fun themeToGson(themes: List<Theme>) : String {
        return  Gson().toJson(themes)
    }

    @TypeConverter
    fun gsonToTheme(json: String) : List<Theme> {
        val listType = object : TypeToken<List<Theme>>(){}.type
        return Gson().fromJson(json, listType)
    }
    @TypeConverter
    fun titleToGson(themes: List<Title>) : String {
        return  Gson().toJson(themes)
    }

    @TypeConverter
    fun gsonToTitle(json: String) : List<Title> {
        val listType = object : TypeToken<List<Title>>(){}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun titleSynonymsToGson(title_synonyms: List<String>) : String {
        return  Gson().toJson(title_synonyms)
    }

    @TypeConverter
    fun gsonToTitleSynonyms(json: String) : List<String> {
        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(json, listType)
    }
}