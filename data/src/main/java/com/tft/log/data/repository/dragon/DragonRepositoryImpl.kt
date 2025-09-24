package com.tft.log.data.repository.dragon

import com.tft.log.data.api.apiService.DragonApiService
import com.tft.log.data.api.dto.TFTChampionResponse
import retrofit2.Response
import javax.inject.Inject

class DragonRepositoryImpl @Inject constructor(
    private val dragonApiService: DragonApiService
) : DragonRepository {
    override suspend fun getVersions(): Response<List<String>> {
        return dragonApiService.getVersions()
    }

    override suspend fun getChampions(version: String): Response<TFTChampionResponse> {
        return dragonApiService.getChampions(version = version)
    }
}