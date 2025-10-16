package com.tft.log.data.api.apiService

import com.tft.log.data.api.dto.LeagueByPuuidResponse
import com.tft.log.data.api.dto.SummonerByPuuidResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface KrRiotApiService {
    @GET("tft/league/v1/by-puuid/{puuid}")
    suspend fun getLeagueByPuuid(
        @Path("puuid") puuid: String
    ): Response<List<LeagueByPuuidResponse>>

    @GET("tft/summoner/v1/summoners/by-puuid/{puuid}")
    suspend fun getSummonerByPuuid(
        @Path("puuid") puuid: String
    ): Response<SummonerByPuuidResponse>
}