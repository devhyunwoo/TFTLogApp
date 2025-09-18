package com.tft.log.data.repository.tft

import com.tft.log.data.api.ApiService
import com.tft.log.data.api.dto.MatchByMatchIdResponse
import retrofit2.Response
import javax.inject.Inject

class TftRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TftRepository {
    override suspend fun getMatchIdsByPuuid(puuid: String): Response<List<String>> {
        return apiService.getMatchIdsByPuuid(puuid = puuid)
    }

    override suspend fun getMatchByMatchId(matchId: String): Response<MatchByMatchIdResponse> {
        return apiService.getMatchByMatchId(matchId = matchId)
    }

}