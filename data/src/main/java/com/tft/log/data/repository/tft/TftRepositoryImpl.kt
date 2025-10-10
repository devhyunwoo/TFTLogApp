package com.tft.log.data.repository.tft

import com.tft.log.data.api.apiService.RiotApiService
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.entity.mapper.toMatchEntity
import com.tft.log.data.repository.dp.DatabaseRepository
import com.tft.log.data.utils.ApiResult
import com.tft.log.data.utils.safeApiCall
import javax.inject.Inject

class TftRepositoryImpl @Inject constructor(
    private val riotApiService: RiotApiService,
    private val db: DatabaseRepository
) : TftRepository {
    override suspend fun getMatchIdsByPuuid(puuid: String): ApiResult<List<String>> {
        return safeApiCall { riotApiService.getMatchIdsByPuuid(puuid = puuid) }
    }

    override suspend fun getMatchByMatchId(puuid: String, matchId: String): ApiResult<MatchEntity> {
        return when (val result =
            safeApiCall { riotApiService.getMatchByMatchId(matchId = matchId) }) {
            is ApiResult.Success -> {
                val championIds =
                    result.data.info.participants.flatMap { participant -> participant.units.map { unit -> unit.characterId.lowercase() } }
                        .distinct()
                val championImages =
                    db.getChampions(championIds)?.associate { it.championId to it.imageName }

                val traitIds =
                    result.data.info.participants.flatMap { participant -> participant.traits.map { trait -> trait.name } }
                        .distinct()
                val traitImages =
                    db.getTraits(traitIds)?.associate { it.traitId to it.imageName }

                ApiResult.Success(
                    data = result.data.toMatchEntity(
                        puuid = puuid,
                        championImages = championImages ?: hashMapOf(),
                        traitImages = traitImages ?: hashMapOf()
                    )
                )
            }

            is ApiResult.Error -> {
                result
            }
        }
    }
}