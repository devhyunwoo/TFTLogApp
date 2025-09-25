package com.tft.log.data.repository.tft

import com.tft.log.data.api.dto.MatchByMatchIdResponse
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.utils.ApiResult
import retrofit2.Response


interface TftRepository {
    suspend fun getMatchIdsByPuuid(puuid: String): ApiResult<List<String>>

    suspend fun getMatchByMatchId(puuid: String, matchId: String): ApiResult<MatchEntity>
}