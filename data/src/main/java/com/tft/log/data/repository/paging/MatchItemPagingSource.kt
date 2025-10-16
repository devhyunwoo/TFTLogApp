package com.tft.log.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.repository.tft.TftRepository

class MatchItemPagingSource(
    private val tftRepository: TftRepository, private val puuid: String
) : PagingSource<Int, MatchEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MatchEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchEntity> {
        return try {
            val currentPage = params.key ?: 1
            val matchIds = tftRepository.getMatchIdsByPuuid(
                start = (currentPage - 1) * PAGE_SIZE, count = PAGE_SIZE, puuid = puuid
            )
            val nextPage = if (matchIds.size != 5) null else currentPage + 1
            val prevPage = if (currentPage == 1) null else currentPage - 1
            val data = matchIds.mapNotNull { matchId ->
                tftRepository.getMatchEntity(puuid = puuid, matchId = matchId)
            }
            LoadResult.Page(
                data = data, nextKey = nextPage, prevKey = prevPage
            )
        } catch (t: Throwable) {
            LoadResult.Error(throwable = t)
        }
    }

    companion object {
        const val PAGE_SIZE = 5
    }
}