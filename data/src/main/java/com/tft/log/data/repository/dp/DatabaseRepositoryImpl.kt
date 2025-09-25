package com.tft.log.data.repository.dp

import com.tft.log.data.entity.ChampionEntity
import com.tft.log.data.room.database.TFTDatabase
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val tftDatabase: TFTDatabase
) : DatabaseRepository {
    override suspend fun getImages(ids: List<String>):List<ChampionEntity>? {
        return tftDatabase.getTFTDao().getChampionEntities(ids)
    }


    override suspend fun setChampionEntities(championEntities: List<ChampionEntity>) {
        tftDatabase.getTFTDao().setChampionEntities(championEntities = championEntities)
    }

}