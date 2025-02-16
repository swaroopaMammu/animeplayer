package com.example.animeplayer.model.reprository

import com.example.animeplayer.model.AnimeData
import com.example.animeplayer.model.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val api: ApiService) {

   suspend fun getAnimeList(): List<AnimeData> {
            try {
                val result = api.getAnimeList()
                return result.data
            }catch (e:Exception){
                println("something went wrong")
                return emptyList()
            }
    }
}