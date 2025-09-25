package com.tft.log.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChampionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("championId")
    val championId: String,
    @ColumnInfo("imageName")
    val imageName: String,
)