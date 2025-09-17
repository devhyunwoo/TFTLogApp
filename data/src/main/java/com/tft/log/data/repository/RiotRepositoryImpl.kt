package com.tft.log.data.repository

import com.tft.log.data.api.ApiService
import com.tft.log.data.api.dto.AccountByRiotIdResponse
import retrofit2.Response
import javax.inject.Inject

class RiotRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RiotRepository {
    override suspend fun getAccountByRiotId(
        gameName: String, tagLine: String
    ): Response<AccountByRiotIdResponse> {
        return apiService.getAccountByRiotId(gameName, tagLine)
    }
}