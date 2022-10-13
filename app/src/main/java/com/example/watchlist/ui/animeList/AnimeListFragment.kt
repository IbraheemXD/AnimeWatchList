package com.example.watchlist.ui.animeList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.watchlist.R
import com.example.watchlist.adapters.AnimeListAdapter
import com.example.watchlist.api.apiResponse.Anime
import com.example.watchlist.databinding.FragmentAnimeListBinding
import com.example.watchlist.ui.AnimeListViewModel
import com.example.watchlist.ui.MainActivity
import com.example.watchlist.utils.Resource

class AnimeListFragment : Fragment() {
    private lateinit var binding: FragmentAnimeListBinding
    private lateinit var viewModel: AnimeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimeListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        viewModel.animeList.observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Success -> {
                    res.data?.let { animeResponse ->
                        setRecyclerView(animeResponse.data)
                    }
                }
                is Resource.Error -> {
                    res.message?.let { message ->
                        Log.e("ERROR", "AN ERROR OCCURRED: \n $message")
                    }
                }
                is Resource.Loading -> {
                    println("Loading")
                }
            }
        }

    }

    private fun setRecyclerView(anime: List<Anime>) {
        val adapter = AnimeListAdapter(anime) {
            findNavController().navigate(R.id.action_animeListFragment_to_animeFragment)
        }
        binding.rvAnimeList.adapter = adapter
    }

}