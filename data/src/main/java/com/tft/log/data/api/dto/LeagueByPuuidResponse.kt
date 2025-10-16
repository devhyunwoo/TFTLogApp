package com.tft.log.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueByPuuidResponse(
    @SerialName("tier")
    val tier: Tier,
    @SerialName("rank")
    val rank: Rank,
    @SerialName("leaguePoints")
    val leaguePoints: Int,
    @SerialName("wins")
    val wins: Int,
    @SerialName("losses")
    val losses: Int,
)

@Serializable
enum class Tier(val displayName: String) {
    @SerialName("IRON")
    IRON("아이언"),

    @SerialName("BRONZE")
    BRONZE("브론즈"),

    @SerialName("SILVER")
    SILVER("실버"),

    @SerialName("GOLD")
    GOLD("골드"),

    @SerialName("PLATINUM")
    PLATINUM("플래티넘"),

    @SerialName("EMERALD")
    EMERALD("에메랄드"),

    @SerialName("DIAMOND")
    DIAMOND("다이아몬드"),

    @SerialName("MASTER")
    MASTER("마스터"),

    @SerialName("GRANDMASTER")
    GRANDMASTER("그랜드마스터"),

    @SerialName("CHALLENGER")
    CHALLENGER("챌린저");
}

@Serializable
enum class Rank(val number: Int) {
    @SerialName("I")
    I(1),

    @SerialName("II")
    II(2),

    @SerialName("III")
    III(3),

    @SerialName("IV")
    IV(4)
}