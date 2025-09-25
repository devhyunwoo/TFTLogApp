package com.tft.log.data.entity.mapper

import com.tft.log.data.api.dto.GameResult
import com.tft.log.data.api.dto.MatchByMatchIdResponse
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.entity.Participant
import com.tft.log.data.entity.Unit
import com.tft.log.data.utils.ImageType
import com.tft.log.data.utils.ImageUtils.createImageUrl
import com.tft.log.data.utils.TimeUtils.toMinutes
import com.tft.log.data.utils.TimeUtils.toTimeFormat
import com.tft.log.data.utils.TimeUtils.toYyMMddHHmm


fun MatchByMatchIdResponse.toMatchEntity(
    puuid: String,
    images: Map<String, String>
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
            lastRound = me.lastRound,
            level = me.level,
            rank = me.placement,
            puuid = me.puuid,
            id = me.riotIdGameName + "#" + me.riotIdTagLine.orEmpty(),
            datetime = me.timeEliminated.toMinutes(),
            win = me.win,
            totalDamage = me.totalDamageToPlayers,
            units = me.units.map {
                Unit(
                    characterImageUrl = createImageUrl(
                        images[it.characterId] ?: it.characterId,
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
            }
        ),
        participants = info.participants.map { participantDTO ->
            Participant(
                goldLeft = participantDTO.goldLeft,
                lastRound = participantDTO.lastRound,
                level = participantDTO.level,
                rank = participantDTO.placement,
                puuid = participantDTO.puuid,
                id = participantDTO.riotIdGameName + "#" + participantDTO.riotIdTagLine.orEmpty(),
                datetime = participantDTO.timeEliminated.toMinutes(),
                totalDamage = participantDTO.totalDamageToPlayers,
                units = participantDTO.units.map {
                    Unit(
                        characterImageUrl = createImageUrl(
                            images[it.characterId] ?: it.characterId,
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
                win = participantDTO.win
            )
        }
    )
}