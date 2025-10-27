package com.tft.log.data.repository.db

import com.tft.log.data.entity.ChampionEntity
import com.tft.log.data.entity.TraitEntity
import com.tft.log.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun getChampions(ids: List<String>): List<ChampionEntity>?

    suspend fun getTraits(ids: List<String>): List<TraitEntity>?

    suspend fun setChampionEntities(championEntities: List<ChampionEntity>)

    suspend fun setTraitEntities(traitEntities: List<TraitEntity>)

    suspend fun setUserEntity(userEntity: UserEntity)

    fun getUserEntities(): Flow<List<UserEntity>>?
}