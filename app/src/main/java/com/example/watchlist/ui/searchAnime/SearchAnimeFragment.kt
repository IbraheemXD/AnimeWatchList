package com.example.watchlist.ui.searchAnime

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.watchlist.R
import com.example.watchlist.adapters.AnimeListAdapter
import com.example.watchlist.api.apiResponse.Anime
import com.example.watchlist.api.apiResponse.AnimeResponse
import com.example.watchlist.databinding.FragmentAnimeBinding
import com.example.watchlist.databinding.FragmentSearchAnimeBinding
import com.example.watchlist.ui.AnimeListViewModel
import com.example.watchlist.ui.MainActivity
import com.example.watchlist.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchAnimeFragment : Fragment() {
    private lateinit var binding: FragmentSearchAnimeBinding
    private lateinit var viewModel: AnimeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchAnimeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        var job: Job? = null
        binding.etSearchAnime.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.getQueriedAnimeList(editable.toString())
                    }
                }
            }
        }

        viewModel.searchAnimeList.observe(viewLifecycleOwner) { res ->
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
            findNavController().navigate(R.id.action_searchAnimeFragment_to_animeFragment)
        }
        binding.rvSearchAnimeList.adapter = adapter
    }

}