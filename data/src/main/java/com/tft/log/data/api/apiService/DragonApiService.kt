package com.tft.log.data.api.apiService

import com.tft.log.data.api.dto.TFTChampionResponse
import com.tft.log.data.api.dto.TFTTraitResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DragonApiService {
    @GET("api/versions.json")
    suspend fun getVersions(): Response<List<String>>

    @GET("cdn/{version}/data/ko_KR/tft-champion.json")
    suspend fun getChampions(
        @Path("version") version: String
    ): Response<TFTChampionResponse>

    @GET("cdn/{version}/data/ko_KR/tft-trait.json")
    suspend fun getTraits(
        @Path("version") version: String
    ): Response<TFTTraitResponse>
}