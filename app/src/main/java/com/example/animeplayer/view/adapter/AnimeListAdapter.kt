package com.example.animeplayer.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animeplayer.databinding.AnimeItemLayoutBinding
import com.example.animeplayer.model.AnimeData

class AnimeListAdapter(
    private var animeList : List<AnimeData>,
    private val onItemClick : (AnimeData) -> Unit
) : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {


    class AnimeViewHolder(val binding:AnimeItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
       val binding = AnimeItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AnimeViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list : List<AnimeData>){
        animeList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return animeList.size
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.binding.anime = animeList[position]
        holder.binding.root.setOnClickListener {
            onItemClick(animeList[position])
        }
    }
}