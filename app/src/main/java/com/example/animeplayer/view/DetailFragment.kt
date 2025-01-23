package com.example.animeplayer.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.animeplayer.databinding.FragmentDetailFragmnetBinding
import com.example.animeplayer.viewModel.DetailFragmentViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailFragmnetBinding

    private val viewModel: DetailFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = FragmentDetailFragmnetBinding.inflate(inflater,container,false)
        val arg : DetailFragmentArgs by navArgs()
        val data = arg.animeDetailsData
        viewModel.animeDetail = data
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailBinding.anime = viewModel.animeDetail

        detailBinding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                viewModel.animeDetail.trailer?.youtube_id?.let {
                    youTubePlayer.cueVideo(it,0F)
                }
            }

        })

    }
}