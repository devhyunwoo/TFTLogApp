package com.tft.log.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TraitEntity(
    @PrimaryKey
    @ColumnInfo("traitId")
    val traitId: String,
    @ColumnInfo("imageName")
    val imageName: String
)