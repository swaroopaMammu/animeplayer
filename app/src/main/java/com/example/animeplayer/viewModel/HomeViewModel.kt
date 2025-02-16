package com.example.animeplayer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeplayer.model.AnimeData
import com.example.animeplayer.model.reprository.RepositoryIf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: RepositoryIf) : ViewModel() {

    private val _animeList = MutableLiveData<List<AnimeData>>()
    val animeList : LiveData<List<AnimeData>> get() = _animeList

    fun getAnimeList(){
        viewModelScope.launch{
            try {
                val result = repo.getAnimeList()
                _animeList.postValue(result)
            }catch (e:Exception){
                _animeList.postValue(emptyList())
                println("something went wrong")
            }
        }
    }
}