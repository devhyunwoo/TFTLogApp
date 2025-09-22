package com.tft.log.data.api.apiService

import com.tft.log.data.api.dto.AccountByRiotIdResponse
import com.tft.log.data.api.dto.MatchByMatchIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApiService {
    // 아이디랑 태그로 puuid 가져오기
    @GET("riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    suspend fun getAccountByRiotId(
        @Path("gameName") gameName: String,
        @Path("tagLine") tagLine: String
    ): Response<AccountByRiotIdResponse>

    // puuid로 매치 아이디 리스트 가져오기
    @GET("tft/match/v1/matches/by-puuid/{puuid}/ids")
    suspend fun getMatchIdsByPuuid(
        @Path("puuid") puuid: String,
    ): Response<List<String>>

    // 매치 아이디로 매치 정보 가져오기
    @GET("tft/match/v1/matches/{matchId}")
    suspend fun getMatchByMatchId(
        @Path("matchId") matchId: String,
    ): Response<MatchByMatchIdResponse>
}