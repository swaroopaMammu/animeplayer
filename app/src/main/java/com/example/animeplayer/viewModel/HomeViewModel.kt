package com.example.animeplayer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeplayer.model.AnimeData
import com.example.animeplayer.model.NetworkClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _animeList = MutableLiveData<List<AnimeData>>()
    val animeList : LiveData<List<AnimeData>> get() = _animeList

    fun getAnimeList(){
        viewModelScope.launch{
            try {
                val result = NetworkClient.apiService.getAnimeList()
                _animeList.postValue(result.data)
            }catch (e:Exception){
                _animeList.postValue(emptyList())
                println("something went wrong")
            }
        }
    }
}