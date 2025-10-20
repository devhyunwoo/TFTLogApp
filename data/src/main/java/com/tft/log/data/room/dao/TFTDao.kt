package com.tft.log.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tft.log.data.entity.ChampionEntity
import com.tft.log.data.entity.TraitEntity

@Dao
interface TFTDao {
    @Query("SELECT * FROM ChampionEntity WHERE championId IN (:championIds)")
    suspend fun getChampionEntities(championIds: List<String>): List<ChampionEntity>?

    @Query("SELECT * FROM TraitEntity WHERE traitId IN (:traitId)")
    suspend fun getTraitEntities(traitId: List<String>): List<TraitEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setChampionEntities(championEntities: List<ChampionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setTraitEntities(traitEntities: List<TraitEntity>)
}
