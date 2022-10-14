package com.example.watchlist.ui.animeList

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
import com.example.watchlist.adapters.CarouselViewAdapter
import com.example.watchlist.api.apiResponse.Anime
import com.example.watchlist.databinding.FragmentAnimeListBinding
import com.example.watchlist.ui.AnimeListViewModel
import com.example.watchlist.ui.MainActivity
import com.example.watchlist.utils.Resource
import java.io.Serializable

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
        val activity = (activity as MainActivity)
        viewModel = activity.viewModel


        viewModel.animeList.observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Success -> {
                    res.data?.let { animeResponse ->
                        setRecyclerView(animeResponse.data.shuffled())
                        setCarouselRecyclerView(animeResponse.data)
                        hideProgressBar()
                    }
                }
                is Resource.Error -> {
                    res.message?.let { message ->
                        Log.e("ERROR", "AN ERROR OCCURRED: \n $message")
                    }
                    hideProgressBar()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }

        viewModel.topAnimeList.observe(viewLifecycleOwner) { res ->
            when (res) {
                is Resource.Success -> {
                    res.data?.let { animeRes ->
                        setCarouselRecyclerView(animeRes.data)
                    }
                }
                is Resource.Error -> {
                    res.message?.let { message ->
                        Log.e("ERROR", "AN ERROR OCCURRED: \n $message")
                    }
                    hideProgressBar()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }

        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity.supportActionBar?.setTitle(R.string.browse_anime)
    }

    private fun setRecyclerView(animeList: List<Anime>) {
        val adapter = AnimeListAdapter(animeList) { id ->
            navigateToAnimeFragment(animeList, id)
        }
        binding.rvAnimeList.adapter = adapter
        binding.rvAnimeList.layoutManager = GridLayoutManager(activity?.applicationContext, 2)
    }

    private fun navigateToAnimeFragment(animeList: List<Anime>, id: Int) {
        val index = animeList.indexOfFirst { anime -> anime.mal_id == id }
        val bundle = Bundle().apply {
            putSerializable("anime", animeList[index])
        }
        findNavController().navigate(
            R.id.action_animeListFragment_to_animeFragment,
            bundle
        )
    }

    private fun setCarouselRecyclerView(animeList: List<Anime>) {
        binding.carouselRecyclerview.adapter = CarouselViewAdapter(animeList) { id ->
            navigateToAnimeFragment(animeList, id)
        }
        binding.carouselRecyclerview.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }
    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}