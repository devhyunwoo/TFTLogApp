package com.tft.log.data.repository.tft

import androidx.paging.PagingData
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.utils.ApiResult
import kotlinx.coroutines.flow.Flow


interface TftRepository {
    suspend fun getMatchIdsByPuuid(
        start: Int,
        count: Int,
        puuid: String
    ): List<String>

    suspend fun getMatchByMatchId(puuid: String, matchId: String): MatchEntity?
}