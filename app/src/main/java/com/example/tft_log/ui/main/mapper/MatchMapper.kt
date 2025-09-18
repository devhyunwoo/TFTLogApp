package com.example.tft_log.ui.main.mapper

import com.tft.log.data.api.dto.GameResult
import com.tft.log.data.api.dto.MatchByMatchIdResponse
import com.tft.log.data.entitiy.MatchEntity
import com.tft.log.data.entitiy.Participant


fun MatchByMatchIdResponse.toMatchEntity(): MatchEntity {
    val info = this.info
    return MatchEntity(
        isGameEnd = info.endOfGameResult == GameResult.GameComplete,
        gameId = info.gameId,
        gameDatetime = info.gameDatetime.toYyMMddHHmm(),
        gameLength = info.gameLength.toTimeFormat(),
        participants = info.participants.map { participantDTO ->
            Participant(
                goldLeft = participantDTO.goldLeft,
                lastRound = participantDTO.lastRound,
                level = participantDTO.level,
                rank = participantDTO.placement,
                puuid = participantDTO.puuid,
                id = participantDTO.riotIdGameName + "#" + participantDTO.riotIdTagLine.orEmpty(),
                datetime = participantDTO.timeEliminated,
                totalDamage = participantDTO.totalDamageToPlayers
            )
        }
    )
}