package com.example.animeplayer.view.binder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.animeplayer.model.Genre
import com.example.animeplayer.model.Producer


@BindingAdapter("animeTitle")
fun TextView.setTitle(title:String){
    this.text = "Title : $title"
}

@BindingAdapter("animeEpisodes")
fun TextView.setEpisodes(value:Int?){
    this.text = "Episodes : ${value?:0}"
}

@BindingAdapter("animeScore")
fun TextView.setScore(value:Float){
    this.text = "Score : ${value}"
}

@BindingAdapter("animeRating")
fun TextView.setRating(value:String){
    this.text = "Rating : $value"
}

@BindingAdapter("animeGenre")
fun TextView.setGenre(list: List<Genre>){
    var genre = "Genre(s) : "
    list.forEach {
        genre += (it.name + "/")
    }
    this.text = genre.removeSuffix("/")
}
@BindingAdapter("animeProducers")
fun TextView.setProducers(list: List<Producer>){
    var producers = "Producers : "
    list.forEach {
        producers += (it.name + ", ")
    }
    this.text = producers.removeSuffix(",")
}

@BindingAdapter("animePoster")
fun ImageView.setImageView(value:String){
    Glide.with(this.context)
        .load(value.toUri())
        .into(this)
}

 @BindingAdapter("isVisible")
 fun View.setVisibility(value: Boolean){
     this.isVisible = value
 }


