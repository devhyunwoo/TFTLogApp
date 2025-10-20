package com.tft.log.data.entity

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class UserEntity(
    @PrimaryKey
    @ColumnInfo("puuid")
    val puuid: String,
    @ColumnInfo("nickname")
    val nickname: String?,
    @ColumnInfo("level")
    val level: String?,
    @ColumnInfo("profileImage")
    val profileImage: String?,
    @ColumnInfo("tier")
    val tier: String?,
    @ColumnInfo("lp")
    val lp: String?,
    @ColumnInfo("wins")
    val wins: String?,
    @ColumnInfo("losses")
    val losses: String?,
    @ColumnInfo("winRate")
    val winRate: String?,
    @ColumnInfo("createdAt")
    val createdAt: Long = System.currentTimeMillis()
)