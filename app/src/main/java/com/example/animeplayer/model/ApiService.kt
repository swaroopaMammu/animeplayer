package com.example.animeplayer.model

import retrofit2.http.GET

interface ApiService {

    @GET("v4/top/anime")
    suspend fun getAnimeList():AnimeListItem

}