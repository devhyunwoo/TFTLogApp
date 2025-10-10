package com.tft.log.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchByMatchIdResponse(
    @SerialName("metadata")
    val metadata: MetadataDTO,
    @SerialName("info")
    val info: InfoDTO
)

@Serializable
data class MetadataDTO(
    @SerialName("data_version")
    val dataVersion: String,
    @SerialName("match_id")
    val matchId: String,
    @SerialName("participants")
    val participants: List<String>
)

@Serializable
data class InfoDTO(
    @SerialName("endOfGameResult")
    val endOfGameResult: GameResult,
    @SerialName("gameCreation")
    val gameCreation: Long,
    @SerialName("gameId")
    val gameId: Long,
    @SerialName("game_datetime")
    val gameDatetime: Long,
    @SerialName("game_length")
    val gameLength: Float,
    @SerialName("game_version")
    val gameVersion: String,
    @SerialName("mapId")
    val mapId: Int,
    @SerialName("participants")
    val participants: List<ParticipantDTO>,
    @SerialName("queue_id")
    val queueId: Int,
    @SerialName("tft_game_type")
    val tftGameType: String,
    @SerialName("tft_set_core_name")
    val tftSetCoreName: String,
    @SerialName("tft_set_number")
    val tftSetNumber: Int,
)

@Serializable
data class ParticipantDTO(
    @SerialName("companion")
    val companion: CompanionDTO,
    @SerialName("gold_left")
    val goldLeft: Int,
    @SerialName("last_round")
    val lastRound: Int,
    @SerialName("level")
    val level: Int,
    @SerialName("placement")
    val placement: Int,
    @SerialName("players_eliminated")
    val playersEliminated: Int,
    @SerialName("puuid")
    val puuid: String,
    @SerialName("riotIdGameName")
    val riotIdGameName: String,
    @SerialName("riotIdTagline")
    val riotIdTagline: String? = null,
    @SerialName("time_eliminated")
    val timeEliminated: Float,
    @SerialName("total_damage_to_players")
    val totalDamageToPlayers: Int,
    @SerialName("traits")
    val traits: List<TraitDTO>,
    @SerialName("units")
    val units: List<UnitDTO>,
    @SerialName("win")
    val win: Boolean
)

@Serializable
data class CompanionDTO(
    @SerialName("content_ID")
    val contentID: String,
    @SerialName("item_ID")
    val itemID: String,
    @SerialName("skin_ID")
    val skinID: String,
    @SerialName("species")
    val species: String
)

@Serializable
data class TraitDTO(
    @SerialName("name")
    val name: String,
    @SerialName("num_units")
    val numUnits: Int,
    @SerialName("style")
    val style: Int,
    @SerialName("tier_current")
    val tierCurrent: Int,
    @SerialName("tier_total")
    val tierTotal: Int
)

@Serializable
data class UnitDTO(
    @SerialName("items")
    val items: List<Int>? = null,
    @SerialName("character_id")
    val characterId: String,
    @SerialName("itemNames")
    val itemNames: List<String>,
    @SerialName("chosen")
    val chosen: String? = null,
    @SerialName("name")
    val name: String,
    @SerialName("rarity")
    val rarity: Int,
    @SerialName("tier")
    val tier: Int
)

@Serializable
enum class GameResult {
    @SerialName("GameComplete")
    GameComplete,
}