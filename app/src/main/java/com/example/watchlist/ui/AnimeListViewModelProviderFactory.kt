package com.example.watchlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watchlist.repository.AnimeRepo

class AnimeListViewModelProviderFactory(
    private val animeRepo: AnimeRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeListViewModel(animeRepo) as T
    }
}