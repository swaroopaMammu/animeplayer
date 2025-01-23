package com.example.animeplayer.viewModel

import androidx.lifecycle.ViewModel
import com.example.animeplayer.model.AnimeData

class DetailFragmentViewModel : ViewModel() {

    lateinit var animeDetail:AnimeData
    var playbackPosition = 0F

}