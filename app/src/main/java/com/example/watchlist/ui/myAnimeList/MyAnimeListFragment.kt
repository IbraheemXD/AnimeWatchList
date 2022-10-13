package com.example.watchlist.ui.myAnimeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentAnimeBinding
import com.example.watchlist.databinding.FragmentMyAnimeListBinding
import com.example.watchlist.ui.AnimeListViewModel
import com.example.watchlist.ui.MainActivity


class MyAnimeListFragment : Fragment() {
    private lateinit var binding: FragmentMyAnimeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyAnimeListBinding.inflate(inflater, container, false)

        return binding.root
    }

}