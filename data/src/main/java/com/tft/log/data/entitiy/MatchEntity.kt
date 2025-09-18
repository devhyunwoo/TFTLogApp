package com.tft.log.data.entitiy

data class MatchEntity(
    val isGameEnd: Boolean,
    val gameId: Long,
    val gameDatetime: String,
    val gameLength: String,
    val participants: List<Participant>
)

data class Participant(
    val goldLeft: Int,
    val lastRound: Int,
    val level: Int,
    val rank: Int,
    val puuid: String,
    val id: String,
    val datetime: Float,
    val totalDamage: Int,
)