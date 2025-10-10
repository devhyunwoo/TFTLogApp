package com.tft.log.data.entity

data class MatchEntity(
    val isGameEnd: Boolean,
    val gameVersion: String,
    val gameId: Long,
    val gameDatetime: String,
    val gameLength: String,
    val me: Participant,
    val participants: List<Participant>
)

data class Participant(
    val goldLeft: Int,
    val lastRound: Int,
    val level: Int,
    val rank: Int,
    val puuid: String,
    val id: String,
    val datetime: String,
    val win: Boolean,
    val totalDamage: Int,
    val units: List<Unit>,
    val traits: List<Trait>
)

data class Unit(
    val characterImageUrl: String,
    val itemsImageUrl: List<String>,
    val rarity: Int,
    val tier: Int
)

data class Trait(
    val imageUrl : String,
    val style : Int
)

