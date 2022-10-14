package com.example.watchlist.ui.myAnimeList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.watchlist.R
import com.example.watchlist.adapters.AnimeListAdapter
import com.example.watchlist.api.apiResponse.Anime
import com.example.watchlist.databinding.FragmentAnimeBinding
import com.example.watchlist.databinding.FragmentMyAnimeListBinding
import com.example.watchlist.ui.AnimeListViewModel
import com.example.watchlist.ui.MainActivity
import com.example.watchlist.utils.Resource


class MyAnimeListFragment : Fragment() {
    private lateinit var binding: FragmentMyAnimeListBinding
    private lateinit var viewModel: AnimeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyAnimeListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = (activity as MainActivity)
        viewModel = activity.viewModel

        viewModel.getSavedAnime().observe(viewLifecycleOwner) { animeList ->
            setRecyclerView(animeList)
        }

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.setTitle(R.string.my_anime_list)

    }
    private fun setRecyclerView(animeList: List<Anime>) {
        val adapter = AnimeListAdapter(animeList) { id ->
            val index = animeList.indexOfFirst { anime -> anime.mal_id == id }
            val bundle = Bundle().apply {
                putSerializable("anime", animeList[index])
            }
            findNavController().navigate(
                R.id.action_myAnimeListFragment_to_animeFragment,
                bundle
            )
        }
        binding.rvMyAnimeList.adapter = adapter
        binding.rvMyAnimeList.layoutManager = GridLayoutManager(activity?.applicationContext, 2)
    }
}