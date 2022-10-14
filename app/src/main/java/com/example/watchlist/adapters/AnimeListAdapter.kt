package com.example.watchlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchlist.R
import com.example.watchlist.api.apiResponse.Anime
import com.example.watchlist.databinding.ItemAnimeListBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class AnimeListAdapter(
    private val animeList: List<Anime>,
    val onClickListener: (id:Int) -> Unit
) : RecyclerView.Adapter<AnimeListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemAnimeListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(anime: Anime, view: View) {
            val score = anime.score ?: "N/A"
            binding.rvAnimeTitle.text = anime.titles!![0].title
            binding.rvAnimeScore.text = score.toString()
            Glide
                .with(view)
                .load(anime.images!!.jpg!!.image_url)
                .placeholder(R.drawable.place_holder)
                .into(binding.rvAnimeBanner)
            binding.rvItemContainer.setOnClickListener {
                onClickListener(anime.mal_id)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemAnimeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val anime = animeList[position]
        holder.itemView.apply {
            holder.bindItem(anime, this)
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}