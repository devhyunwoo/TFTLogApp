package com.tft.log.data.api

import com.tft.log.data.api.dto.AccountByRiotIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // 아이디랑 태그로 puuid 가져오기
    @GET("riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getAccountByRiotId(
        @Path("gameName") gameName: String,
        @Path("tagLine") tagLine: String
    ): Response<AccountByRiotIdResponse>
}