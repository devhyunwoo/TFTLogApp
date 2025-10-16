package com.tft.log.data.repository.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.repository.paging.MatchItemPagingSource.Companion.PAGE_SIZE
import com.tft.log.data.repository.tft.TftRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(
    private val tftRepository: TftRepository
) : PagingRepository {
    override suspend fun getMatchPagingData(puuid: String): Flow<PagingData<MatchEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                prefetchDistance = 1,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                MatchItemPagingSource(
                    tftRepository = tftRepository, puuid = puuid
                )
            }
        ).flow
    }
}