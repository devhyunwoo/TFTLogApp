package com.tft.log.data.repository.paging

import androidx.paging.PagingData
import com.tft.log.data.entity.MatchEntity
import kotlinx.coroutines.flow.Flow

interface PagingRepository {

    suspend fun getMatchPagingData(puuid: String): Flow<PagingData<MatchEntity>>
}