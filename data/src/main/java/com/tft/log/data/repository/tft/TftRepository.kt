package com.tft.log.data.repository.tft

import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.entity.UserEntity


interface TftRepository {
    suspend fun getMatchIdsByPuuid(
        start: Int,
        count: Int,
        puuid: String
    ): List<String>

    suspend fun getMatchEntity(puuid: String, matchId: String): MatchEntity?

    suspend fun getUserEntity(puuid : String) : UserEntity?
}