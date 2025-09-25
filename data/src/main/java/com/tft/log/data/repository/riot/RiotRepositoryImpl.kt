package com.tft.log.data.repository.riot

import com.tft.log.data.api.apiService.RiotApiService
import com.tft.log.data.api.dto.AccountByRiotIdResponse
import com.tft.log.data.utils.ApiResult
import com.tft.log.data.utils.safeApiCall
import javax.inject.Inject

class RiotRepositoryImpl @Inject constructor(
    private val riotApiService: RiotApiService
) : RiotRepository {
    override suspend fun getAccountByRiotId(
        gameName: String, tagLine: String
    ): ApiResult<AccountByRiotIdResponse> {
        return safeApiCall { riotApiService.getAccountByRiotId(gameName, tagLine) }
    }
}