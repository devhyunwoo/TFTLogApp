package com.tft.log.data.repository.tft

import com.tft.log.data.api.apiService.AsiaRiotApiService
import com.tft.log.data.api.apiService.KrRiotApiService
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.entity.UserEntity
import com.tft.log.data.entity.mapper.Mapper.toMatchEntity
import com.tft.log.data.entity.mapper.Mapper.toUserEntity
import com.tft.log.data.repository.dp.DatabaseRepository
import com.tft.log.data.utils.ApiResult
import com.tft.log.data.utils.safeApiCall
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class TftRepositoryImpl @Inject constructor(
    private val asiaRiotApiService: AsiaRiotApiService,
    private val krRiotApiService: KrRiotApiService,
    private val db: DatabaseRepository
) : TftRepository {
    override suspend fun getMatchIdsByPuuid(
        start: Int, count: Int, puuid: String
    ): List<String> {
        return when (val result = safeApiCall {
            asiaRiotApiService.getMatchIdsByPuuid(
                start = start, count = count, puuid = puuid
            )
        }) {
            is ApiResult.Success -> {
                result.data
            }

            is ApiResult.Error -> {
                emptyList()
            }
        }
    }


    override suspend fun getMatchEntity(puuid: String, matchId: String): MatchEntity? {
        return when (val result =
            safeApiCall { asiaRiotApiService.getMatchByMatchId(matchId = matchId) }) {
            is ApiResult.Success -> {
                val championIds =
                    result.data.info.participants.flatMap { participant -> participant.units.map { unit -> unit.characterId.lowercase() } }
                        .distinct()
                val championImages =
                    db.getChampions(championIds)?.associate { it.championId to it.imageName }

                val traitIds =
                    result.data.info.participants.flatMap { participant -> participant.traits.map { trait -> trait.name.lowercase() } }
                        .distinct()
                val traitImages = db.getTraits(traitIds)?.associate { it.traitId to it.imageName }
                result.data.toMatchEntity(
                    puuid = puuid,
                    championImages = championImages ?: hashMapOf(),
                    traitImages = traitImages ?: hashMapOf()
                )
            }

            is ApiResult.Error -> {
                null
            }
        }
    }

    override suspend fun getUserEntity(puuid: String): UserEntity? {
        return coroutineScope {
            val deferredAccount =
                async { safeApiCall { asiaRiotApiService.getAccountByPuuid(puuid) } }
            val deferredLeague =
                async { safeApiCall { krRiotApiService.getLeagueByPuuid(puuid) } }
            val deferredSummoner =
                async { safeApiCall { krRiotApiService.getSummonerByPuuid(puuid) } }

            val accountResult = deferredAccount.await()
            val leagueResult = deferredLeague.await()
            val summonerResult = deferredSummoner.await()

            val accountData = accountResult as? ApiResult.Success
            val leagueData = leagueResult as? ApiResult.Success
            val summonerData = summonerResult as? ApiResult.Success
            val a = leagueResult as? ApiResult.Error
            summonerData?.data?.toUserEntity(
                account = accountData?.data, league = leagueData?.data?.firstOrNull()
            )
        }
    }
}