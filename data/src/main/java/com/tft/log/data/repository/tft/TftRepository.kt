package com.tft.log.data.repository.tft

import com.tft.log.data.api.dto.MatchByMatchIdResponse
import retrofit2.Response


interface TftRepository {
    suspend fun getMatchIdsByPuuid(puuid: String): Response<List<String>>

    suspend fun getMatchByMatchId(matchId: String): Response<MatchByMatchIdResponse>
}