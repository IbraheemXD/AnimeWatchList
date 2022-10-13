package com.example.watchlist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.api.apiResponse.AnimeResponse
import com.example.watchlist.repository.AnimeRepo
import com.example.watchlist.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class AnimeListViewModel(
    val animeRepo: AnimeRepo
) : ViewModel() {

    val searchAnimeList: MutableLiveData<Resource<AnimeResponse>> = MutableLiveData()
    val animeList: MutableLiveData<Resource<AnimeResponse>> = MutableLiveData()
    val pageNumber = 1
    val limit = 10


    init {
        getAnimeList()
    }


    private fun getAnimeList() = viewModelScope.launch {
        animeList.postValue(Resource.Loading())
        val res = animeRepo.getAnimeList(pageNumber, limit)
        animeList.postValue(handleAnimeListResponse(res))
    }

    fun getQueriedAnimeList(query: String) = viewModelScope.launch {
        searchAnimeList.postValue(Resource.Loading())
        val res = animeRepo.queryAnime(query)
        searchAnimeList.postValue(handleAnimeQueryResponse(res))
    }

    private fun handleAnimeListResponse(res: Response<AnimeResponse>): Resource<AnimeResponse> {
        if (res.isSuccessful) {
            res.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(res.message())
    }

    private fun handleAnimeQueryResponse(res: Response<AnimeResponse>): Resource<AnimeResponse> {
        if (res.isSuccessful) {
            res.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(res.message())
    }
}