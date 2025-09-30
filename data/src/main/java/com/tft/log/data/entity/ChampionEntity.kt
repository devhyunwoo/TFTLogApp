package com.tft.log.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChampionEntity(
    @PrimaryKey
    @ColumnInfo("championId")
    val championId: String,
    @ColumnInfo("imageName")
    val imageName: String,
)