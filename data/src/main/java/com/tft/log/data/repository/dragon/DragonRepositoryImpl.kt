package com.tft.log.data.repository.dragon

import com.tft.log.data.api.apiService.DragonApiService
import com.tft.log.data.api.dto.TFTChampionResponse
import com.tft.log.data.api.dto.TFTTraitResponse
import com.tft.log.data.utils.ApiResult
import com.tft.log.data.utils.safeApiCall
import javax.inject.Inject

class DragonRepositoryImpl @Inject constructor(
    private val dragonApiService: DragonApiService
) : DragonRepository {
    override suspend fun getVersions(): ApiResult<List<String>> {
        return safeApiCall { dragonApiService.getVersions() }
    }

    override suspend fun getChampions(version: String): ApiResult<TFTChampionResponse> {
        return safeApiCall { dragonApiService.getChampions(version = version) }
    }

    override suspend fun getTraits(version: String): ApiResult<TFTTraitResponse> {
        return safeApiCall { dragonApiService.getTraits(version = version) }
    }
}