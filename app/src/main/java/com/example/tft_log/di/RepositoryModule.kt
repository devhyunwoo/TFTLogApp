package com.example.tft_log.di

import com.tft.log.data.repository.RiotRepository
import com.tft.log.data.repository.RiotRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsRiotRepository(
        riotRepository: RiotRepositoryImpl
    ): RiotRepository
}