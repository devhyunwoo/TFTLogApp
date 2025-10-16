package com.tft.log.data.repository.riot

import com.tft.log.data.api.apiService.AsiaRiotApiService
import com.tft.log.data.api.dto.AccountResponse
import com.tft.log.data.utils.ApiResult
import com.tft.log.data.utils.safeApiCall
import javax.inject.Inject

class RiotRepositoryImpl @Inject constructor(
    private val asiaRiotApiService: AsiaRiotApiService
) : RiotRepository {
    override suspend fun getAccountByRiotId(
        gameName: String, tagLine: String
    ): ApiResult<AccountResponse> {
        return safeApiCall { asiaRiotApiService.getAccountByRiotId(gameName, tagLine) }
    }
}