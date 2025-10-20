package com.tft.log.data.entity.mapper

import android.annotation.SuppressLint
import com.tft.log.data.api.dto.AccountResponse
import com.tft.log.data.api.dto.GameResult
import com.tft.log.data.api.dto.LeagueByPuuidResponse
import com.tft.log.data.api.dto.MatchByMatchIdResponse
import com.tft.log.data.api.dto.SummonerByPuuidResponse
import com.tft.log.data.api.dto.TFTChampionResponse
import com.tft.log.data.api.dto.TFTTraitResponse
import com.tft.log.data.entity.ChampionEntity
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.entity.Participant
import com.tft.log.data.entity.Trait
import com.tft.log.data.entity.TraitEntity
import com.tft.log.data.entity.Unit
import com.tft.log.data.entity.UserEntity
import com.tft.log.data.utils.CommonUtils.formatTftRound
import com.tft.log.data.utils.ImageType
import com.tft.log.data.utils.ImageUtils.createImageUrl
import com.tft.log.data.utils.TimeUtils.toMinutes
import com.tft.log.data.utils.TimeUtils.toTimeFormat
import com.tft.log.data.utils.TimeUtils.toYyMMddHHmm

object Mapper {
    fun TFTChampionResponse.toChampionEntity(): List<ChampionEntity> {
        return this.data.map {
            ChampionEntity(
                championId = it.value.id.lowercase(),
                imageName = it.value.image.full
            )
        }
    }


    fun MatchByMatchIdResponse.toMatchEntity(
        puuid: String,
        championImages: Map<String, String>,
        traitImages: Map<String, String>
    ): MatchEntity {
        val info = this.info
        val me = info.participants.first { it.puuid == puuid }
        return MatchEntity(
            isGameEnd = info.endOfGameResult == GameResult.GameComplete,
            gameId = info.gameId,
            gameVersion = info.gameVersion,
            gameDatetime = info.gameDatetime.toYyMMddHHmm(),
            gameLength = info.gameLength.toTimeFormat(),
            me = Participant(
                goldLeft = me.goldLeft,
                lastRound = formatTftRound(me.lastRound),
                level = me.level,
                rank = me.placement,
                puuid = me.puuid,
                id = me.riotIdGameName + "#" + me.riotIdTagline,
                datetime = me.timeEliminated.toMinutes(),
                win = me.win,
                totalDamage = me.totalDamageToPlayers,
                units = me.units.map {
                    com.tft.log.data.entity.Unit(
                        characterImageUrl = createImageUrl(
                            id = championImages[it.characterId.lowercase()] ?: it.characterId,
                            type = ImageType.CHAMPION.type,
                            version = info.gameVersion
                        ),
                        itemsImageUrl = it.itemNames.map { itemName ->
                            createImageUrl(
                                id = itemName,
                                type = ImageType.ITEM.type,
                                version = info.gameVersion
                            )
                        },
                        rarity = it.rarity,
                        tier = it.tier
                    )
                },
                traits = me.traits.filter { it.style != 0 }.map {
                    Trait(
                        imageUrl = createImageUrl(
                            id = traitImages[it.name.lowercase()] ?: it.name,
                            type = ImageType.TRAIT.type,
                            version = info.gameVersion
                        ),
                        style = it.style
                    )
                }.sortedBy { it.style }
            ),
            participants = info.participants.map { participantDTO ->
                Participant(
                    goldLeft = participantDTO.goldLeft,
                    lastRound = formatTftRound(participantDTO.lastRound),
                    level = participantDTO.level,
                    rank = participantDTO.placement,
                    puuid = participantDTO.puuid,
                    id = participantDTO.riotIdGameName + "#" + participantDTO.riotIdTagline,
                    datetime = participantDTO.timeEliminated.toMinutes(),
                    totalDamage = participantDTO.totalDamageToPlayers,
                    units = participantDTO.units.map {
                        Unit(
                            characterImageUrl = createImageUrl(
                                championImages[it.characterId.lowercase()] ?: it.characterId,
                                ImageType.CHAMPION.type,
                                info.gameVersion
                            ),
                            itemsImageUrl = it.itemNames.map { itemName ->
                                createImageUrl(
                                    itemName,
                                    ImageType.ITEM.type,
                                    info.gameVersion
                                )
                            },
                            rarity = it.rarity,
                            tier = it.tier
                        )
                    }.sortedBy { it.rarity },
                    win = participantDTO.win,
                    traits = participantDTO.traits.filter { it.style != 0 }.map {
                        Trait(
                            imageUrl = createImageUrl(
                                id = traitImages[it.name.lowercase()] ?: it.name,
                                type = ImageType.TRAIT.type,
                                version = info.gameVersion
                            ),
                            style = it.style
                        )
                    }.sortedBy { it.style }
                )
            }.sortedBy { it.rank }
        )
    }

    fun TFTTraitResponse.toTraitEntity(): List<TraitEntity> {
        return this.data.map {
            TraitEntity(
                traitId = it.value.id.lowercase(),
                imageName = it.value.image.full
            )
        }
    }

    @SuppressLint("DefaultLocale")
    fun SummonerByPuuidResponse.toUserEntity(
        account: AccountResponse?,
        league: LeagueByPuuidResponse?
    ): UserEntity {

        val tier = league?.let {
            "${it.tier.displayName}${it.rank.number}"
        }

        val lp = league?.let {
            "${it.leaguePoints}LP"
        }

        val winRate = if (league != null && league.wins + league.losses > 0) {
            String.format(
                "%.1f",
                (league.wins.toDouble() / (league.wins + league.losses) * 100)
            ) + "%"
        } else {
            null
        }
        val wins = league?.let { "${it.wins}승" }
        val losses = league?.let { "${it.losses}패" }
        return UserEntity(
            puuid = account?.puuid ?: "",
            nickname = "${account?.gameName}#${account?.tagLine}",
            level = summonerLevel.toString(),
            profileImage = createImageUrl(
                id = profileIconId.toString(),
                type = ImageType.PROFILE.type,
                version = "15.1.1"
            ),
            tier = tier,
            lp = lp,
            wins = wins,
            losses = losses,
            winRate = winRate
        )
    }
}