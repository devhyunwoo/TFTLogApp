package com.example.tft_log.di

import com.tft.log.data.repository.riot.RiotRepository
import com.tft.log.data.repository.riot.RiotRepositoryImpl
import com.tft.log.data.repository.tft.TftRepository
import com.tft.log.data.repository.tft.TftRepositoryImpl
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

    @Binds
    fun bindsTftRepository(
        tftRepository: TftRepositoryImpl
    ): TftRepository
}