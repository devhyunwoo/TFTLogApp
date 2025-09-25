package com.tft.log.data.repository.dp

import com.tft.log.data.entity.ChampionEntity

interface DatabaseRepository {
    suspend fun getImages(ids: List<String>): List<ChampionEntity>?

    suspend fun setChampionEntities(championEntities: List<ChampionEntity>)
}