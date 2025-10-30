package com.woo.tft_log.di

import com.tft.log.data.repository.datastore.DatastoreRepository
import com.tft.log.data.repository.datastore.DatastoreRepositoryImpl
import com.tft.log.data.repository.db.DatabaseRepository
import com.tft.log.data.repository.db.DatabaseRepositoryImpl
import com.tft.log.data.repository.dragon.DragonRepository
import com.tft.log.data.repository.dragon.DragonRepositoryImpl
import com.tft.log.data.repository.paging.PagingRepository
import com.tft.log.data.repository.paging.PagingRepositoryImpl
import com.tft.log.data.repository.riot.RiotRepository
import com.tft.log.data.repository.riot.RiotRepositoryImpl
import com.tft.log.data.repository.tft.TftRepository
import com.tft.log.data.repository.tft.TftRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindsRiotRepository(
        riotRepository: RiotRepositoryImpl
    ): RiotRepository

    @Binds
    @Singleton
    fun bindsTftRepository(
        tftRepository: TftRepositoryImpl
    ): TftRepository

    @Binds
    @Singleton
    fun bindsDragonRepository(
        dragonRepository: DragonRepositoryImpl
    ): DragonRepository

    @Binds
    @Singleton
    fun bindsDatastoreRepository(
        datastoreRepository: DatastoreRepositoryImpl
    ): DatastoreRepository

    @Binds
    @Singleton
    fun bindsDatabaseRepository(
        databaseRepository: DatabaseRepositoryImpl
    ): DatabaseRepository

    @Binds
    @Singleton
    fun bindPagingRepository(
        pagingRepository: PagingRepositoryImpl
    ): PagingRepository
}