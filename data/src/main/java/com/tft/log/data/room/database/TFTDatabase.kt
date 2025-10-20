package com.tft.log.data.room.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.tft.log.data.entity.ChampionEntity
import com.tft.log.data.entity.TraitEntity
import com.tft.log.data.entity.UserEntity
import com.tft.log.data.room.dao.TFTDao
import com.tft.log.data.room.dao.UserDao

@Database(
    entities = [ChampionEntity::class, TraitEntity::class, UserEntity::class],
    version = 5,
)
abstract class TFTDatabase : RoomDatabase() {
    abstract fun getTFTDao(): TFTDao

    abstract fun getUserDao(): UserDao

}