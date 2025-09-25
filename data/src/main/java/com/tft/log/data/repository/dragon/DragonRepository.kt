package com.tft.log.data.repository.dragon

import com.tft.log.data.api.dto.TFTChampionResponse
import com.tft.log.data.utils.ApiResult

interface DragonRepository {
    suspend fun getVersions(): ApiResult<List<String>>
    suspend fun getChampions(version: String): ApiResult<TFTChampionResponse>
}