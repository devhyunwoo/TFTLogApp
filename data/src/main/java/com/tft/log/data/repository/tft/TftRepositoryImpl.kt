package com.tft.log.data.repository.tft

import com.tft.log.data.api.apiService.RiotApiService
import com.tft.log.data.api.dto.MatchByMatchIdResponse
import retrofit2.Response
import javax.inject.Inject

class TftRepositoryImpl @Inject constructor(
    private val riotApiService: RiotApiService
) : TftRepository {
    override suspend fun getMatchIdsByPuuid(puuid: String): Response<List<String>> {
        return riotApiService.getMatchIdsByPuuid(puuid = puuid)
    }

    override suspend fun getMatchByMatchId(matchId: String): Response<MatchByMatchIdResponse> {
        return riotApiService.getMatchByMatchId(matchId = matchId)
    }

}