package com.example.animeplayer.model.reprository

import com.example.animeplayer.model.AnimeData

interface RepositoryIf {
    suspend fun getAnimeList(): List<AnimeData>
}