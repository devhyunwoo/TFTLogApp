package com.tft.log.data.repository.riot

import com.tft.log.data.api.dto.AccountByRiotIdResponse
import com.tft.log.data.utils.ApiResult

interface RiotRepository {
    suspend fun getAccountByRiotId(
        gameName: String,
        tagLine: String
    ): ApiResult<AccountByRiotIdResponse>
}