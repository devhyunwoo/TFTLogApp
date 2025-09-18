package com.tft.log.data.repository.riot

import com.tft.log.data.api.dto.AccountByRiotIdResponse
import retrofit2.Response

interface RiotRepository {
    suspend fun getAccountByRiotId(gameName: String, tagLine: String): Response<AccountByRiotIdResponse>
}