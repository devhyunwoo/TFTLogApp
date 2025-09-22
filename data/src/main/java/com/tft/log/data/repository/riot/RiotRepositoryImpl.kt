package com.tft.log.data.repository.riot

import com.tft.log.data.api.apiService.RiotApiService
import com.tft.log.data.api.dto.AccountByRiotIdResponse
import retrofit2.Response
import javax.inject.Inject

class RiotRepositoryImpl @Inject constructor(
    private val riotApiService: RiotApiService
) : RiotRepository {
    override suspend fun getAccountByRiotId(
        gameName: String, tagLine: String
    ): Response<AccountByRiotIdResponse> {
        return riotApiService.getAccountByRiotId(gameName, tagLine)
    }
}