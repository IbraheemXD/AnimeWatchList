package com.example.watchlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.watchlist.R
import com.example.watchlist.api.apiResponse.Anime
import com.example.watchlist.databinding.ItemCarouselViewBinding

class CarouselViewAdapter(
    private val animeList: List<Anime>,
    private val onCarouselClick: (id:Int) -> Unit
) : RecyclerView.Adapter<CarouselViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCarouselViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(anime: Anime, view: View) {
            Glide
                .with(view)
                .load(anime.images!!.jpg!!.image_url)
                .placeholder(R.drawable.place_holder)
                .into(binding.rvCarouselImage)
            binding.rvCarouselImage.setOnClickListener {
                onCarouselClick(anime.mal_id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCarouselViewBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val anime = animeList[position]
        holder.bindItem(anime, holder.itemView)
    }

    override fun getItemCount(): Int = animeList.size

}