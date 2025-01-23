package com.example.animeplayer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class AnimeListItem(
     val data:List<AnimeData>
)

@Parcelize
data class AnimeData(
    val images:Image,
    val trailer :Trailer?,
    val title :String,
    val episodes :Int?,
    val rating : String?,
    val synopsis:String?,
    val genres :List<Genre>,
    val producers: List<Producer>
):Parcelable

@Parcelize
data class Image(
     val jpg :JPG
):Parcelable

@Parcelize
data class JPG(
     val image_url:String
):Parcelable

@Parcelize
data class Genre(
    val mal_id: Int,
    val type: String?,
    val name: String,
    val url: String
):Parcelable

@Parcelize
data class Trailer(
     val youtube_id : String?,
     val url: String?,
     val embed_url: String?,
):Parcelable

@Parcelize
data class Producer(
   val name : String,
):Parcelable
