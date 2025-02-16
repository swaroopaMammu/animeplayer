package com.example.animeplayer.di

import com.example.animeplayer.model.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeModule {

    @Provides
    @Singleton
    fun getRetrofitClient():Retrofit{
        val BASE_URL = "https://api.jikan.moe/"
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService{
       return retrofit.create(ApiService::class.java)
    }

}