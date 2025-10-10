package com.tft.log.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tft.log.data.entity.ChampionEntity
import com.tft.log.data.entity.TraitEntity
import com.tft.log.data.room.dao.TFTDao

@Database(entities = [ChampionEntity::class, TraitEntity::class], version = 4)
abstract class TFTDatabase : RoomDatabase() {
    abstract fun getTFTDao(): TFTDao
}