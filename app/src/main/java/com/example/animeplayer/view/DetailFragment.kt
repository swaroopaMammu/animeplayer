package com.example.animeplayer.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.animeplayer.databinding.FragmentDetailFragmnetBinding
import com.example.animeplayer.viewModel.DetailFragmentViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailFragmnetBinding

    private val viewModel: DetailFragmentViewModel by viewModels()
    private var playerState = PlayerConstants.PlayerState.UNSTARTED


    companion object {
        val IS_PLAYING = "isPlaying"
    }

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val flag = playerState == PlayerConstants.PlayerState.PLAYING
        outState.putBoolean(IS_PLAYING,flag)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailBinding.anime = viewModel.animeDetail
        detailBinding.appbar.headline = viewModel.animeDetail.title
        detailBinding.appbar.backpress.isVisible = true

        detailBinding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                detailBinding.progressbar.isVisible = false
                    viewModel.animeDetail.trailer?.youtube_id?.let {
                        if(savedInstanceState != null && savedInstanceState.getBoolean(IS_PLAYING)){
                            youTubePlayer.loadVideo(it,viewModel.playbackPosition)
                        }else{
                            youTubePlayer.cueVideo(it,viewModel.playbackPosition)
                        }
                    }

            }
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                super.onStateChange(youTubePlayer, state)
                playerState = state
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                super.onCurrentSecond(youTubePlayer, second)
                viewModel.playbackPosition = second
            }

        })

        detailBinding.appbar.backpress.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}