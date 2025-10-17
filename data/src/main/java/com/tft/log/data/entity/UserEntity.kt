package com.tft.log.data.entity

import androidx.compose.runtime.Immutable

@Immutable
data class UserEntity(
    val puuid: String?,
    val nickname: String?,
    val level: String?,
    val profileImage: String?,
    val tier: String?,
    val lp: String?,
    val wins: String?,
    val losses: String?,
    val winRate: String?
)