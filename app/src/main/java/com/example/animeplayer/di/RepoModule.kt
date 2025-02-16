package com.example.animeplayer.di

import com.example.animeplayer.model.reprository.Repository
import com.example.animeplayer.model.reprository.RepositoryIf
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun getRepository(imp:Repository): RepositoryIf
}