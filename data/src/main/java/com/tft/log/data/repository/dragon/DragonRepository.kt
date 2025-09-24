package com.tft.log.data.repository.dragon

import com.tft.log.data.api.dto.TFTChampionResponse
import retrofit2.Response

interface DragonRepository {
    suspend fun getVersions(): Response<List<String>>
    suspend fun getChampions(version: String): Response<TFTChampionResponse>
}