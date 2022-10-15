package com.example.watchlist.ui.anime

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.watchlist.R
import com.example.watchlist.databinding.FragmentAnimeBinding
import com.example.watchlist.ui.AnimeListViewModel
import com.example.watchlist.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

/**
 * TODO ( handle NPE )
 *
 * */

class AnimeFragment : Fragment() {
    private lateinit var binding: FragmentAnimeBinding
    private lateinit var viewModel: AnimeListViewModel
    private lateinit var youTubePlayer: YouTubePlayerView

    private val args: AnimeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting our ViewModel and NavArgs
        val activity = (activity as MainActivity)
        viewModel = activity.viewModel
        val anime = args.anime
        val animeTitle = anime.titles!![0].title
        // setting our views
        binding.fullAnimeSynopsis.text = handleNPE(anime.synopsis).toString()
        binding.fullAnimeTitle.text =
            "${handleNPE(anime.title_english)} | ${handleNPE(anime.title_japanese)}"
        binding.fullAnimeDuration.text =
            "${handleNPE(anime.year)} | ${handleNPE(anime.episodes)} episodes"
        binding.fullAnimeRating.text = handleNPE(anime.rating).toString()
        binding.fullAnimeEpDuration.text = handleNPE(anime.duration).toString()
        binding.fullAnimeBroadcast.text = handleNPE(anime.broadcast?.string).toString()

        val genres = anime.genres
        var genreString = ""
        // generating a string from array of genre like 'Action | Comedy'
        for (genre in genres!!) {
            if (handleNPE(genre.name) != "N/A") {
                // if its last genre don't add '|' at last
                genreString +=
                    if (genre.name == genres[genres.size - 1].name) {
                        "${handleNPE(genre.name)}"
                    } else {
                        "${handleNPE(genre.name)} | "
                    }
            }
        }

        binding.fullAnimeGenre.text = genreString

        Glide
            .with(this)
            .load(anime.images!!.jpg!!.image_url)
            .placeholder(R.drawable.place_holder)
            .into(binding.fullAnimeImg)

        // setting our read-more function
        val synopsis = anime.synopsis!!
        var isReadingMore = false
        if (synopsis.length > 230) {
            binding.fullAnimeReadmoreBtn.visibility = View.VISIBLE
        }
        binding.fullAnimeReadmoreBtn.setOnClickListener {
            if (!isReadingMore) {
                binding.fullAnimeSynopsis.maxLines = (synopsis.length / 40)
                binding.fullAnimeReadmoreBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                isReadingMore = true
            } else {
                binding.fullAnimeSynopsis.maxLines = 4
                binding.fullAnimeReadmoreBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                isReadingMore = false
            }
        }

        // setting youTubePlayer for youtubePlayerView
        youTubePlayer = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayer)
        youTubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = anime.trailer!!.youtube_id.toString()
                youTubePlayer.loadVideo(videoId, 0f)
            }
        })


        // setting toolbar and bottom navigation menu
        binding.saveAnimeBtn.setOnClickListener {
            viewModel.saveAnimeInDb(anime)
            Snackbar.make(view, "Anime saved", Snackbar.LENGTH_SHORT).show()
        }
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.title = animeTitle
    }

    private fun handleNPE(args: Any?) = try {
        args!!
    } catch (e: NullPointerException) {
        "N/A"
    }
}