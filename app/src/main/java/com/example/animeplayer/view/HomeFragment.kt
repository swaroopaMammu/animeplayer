package com.example.animeplayer.view

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.animeplayer.R
import com.example.animeplayer.databinding.FragmentHomeBinding
import com.example.animeplayer.model.AnimeData
import com.example.animeplayer.view.adapter.AnimeListAdapter
import com.example.animeplayer.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter :AnimeListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        homeBinding.error = false
        homeBinding.appbar.headline = getString(R.string.fav_animes_headline)
        adapter = AnimeListAdapter(emptyList()) { data ->
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragmnet(data)
            findNavController().navigate(action)
        }
        homeBinding.animeListView.adapter = adapter
        viewModel.getAnimeList()
        return homeBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.animeList.observe(viewLifecycleOwner){ data ->
            homeBinding.progressbar.isVisible = false
            if(data.isEmpty()){
                homeBinding.error = true
            }else{
                adapter.setData(data)
            }
        }
    }

}