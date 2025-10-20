package com.tft.log.data.repository.db

import com.tft.log.data.entity.ChampionEntity
import com.tft.log.data.entity.TraitEntity
import com.tft.log.data.entity.UserEntity
import com.tft.log.data.room.database.TFTDatabase
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val tftDatabase: TFTDatabase
) : DatabaseRepository {
    override suspend fun getChampions(ids: List<String>): List<ChampionEntity>? {
        return tftDatabase.getTFTDao().getChampionEntities(championIds = ids)
    }

    override suspend fun getTraits(ids: List<String>): List<TraitEntity>? {
        return tftDatabase.getTFTDao().getTraitEntities(traitId = ids)
    }


    override suspend fun setChampionEntities(championEntities: List<ChampionEntity>) {
        tftDatabase.getTFTDao().setChampionEntities(championEntities = championEntities)
    }

    override suspend fun setTraitEntities(traitEntities: List<TraitEntity>) {
        tftDatabase.getTFTDao().setTraitEntities(traitEntities = traitEntities)
    }

    override suspend fun setUserEntity(userEntity: UserEntity) {
        tftDatabase.getUserDao().setUserEntity(userEntity = userEntity)
    }

    override suspend fun getUserEntities(): List<UserEntity>? {
        return tftDatabase.getUserDao().getUserEntities()
    }

}