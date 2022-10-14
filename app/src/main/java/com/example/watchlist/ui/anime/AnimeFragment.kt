package com.example.watchlist.ui.anime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.watchlist.R
import com.example.watchlist.api.apiResponse.Anime
import com.example.watchlist.databinding.FragmentAnimeBinding
import com.example.watchlist.ui.AnimeListViewModel
import com.example.watchlist.ui.MainActivity
import com.google.android.material.snackbar.Snackbar


class AnimeFragment : Fragment() {
    private lateinit var binding: FragmentAnimeBinding
    private lateinit var viewModel: AnimeListViewModel

    val args: AnimeFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity =  (activity as MainActivity)
        viewModel = activity.viewModel

        val anime = args.anime
        val animeTitle = anime.title_english ?: anime.titles!![0].title
        binding.animeBgDisc.text = anime.background ?: anime.synopsis
        binding.animeTitle.text = animeTitle

        Glide
            .with(this)
            .load(anime.trailer!!.images!!.maximum_image_url)
            .placeholder(R.drawable.place_holder)
            .into(binding.animeMaxImg)


        binding.saveAnimeBtn.setOnClickListener {
            viewModel.saveAnimeInDb(anime)
            Snackbar.make(view, "Anime saved", Snackbar.LENGTH_SHORT).show()
        }
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = animeTitle
    }

}