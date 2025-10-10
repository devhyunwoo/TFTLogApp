package com.tft.log.data.repository.dp

import com.tft.log.data.entity.ChampionEntity
import com.tft.log.data.entity.TraitEntity

interface DatabaseRepository {
    suspend fun getChampions(ids: List<String>): List<ChampionEntity>?

    suspend fun getTraits(ids: List<String>): List<TraitEntity>?

    suspend fun setChampionEntities(championEntities: List<ChampionEntity>)

    suspend fun setTraitEntities(traitEntities: List<TraitEntity>)
}