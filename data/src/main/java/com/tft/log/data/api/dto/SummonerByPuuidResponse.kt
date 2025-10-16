package com.tft.log.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SummonerByPuuidResponse(
    @SerialName("profileIconId")
    val profileIconId: Int,
    @SerialName("summonerLevel")
    val summonerLevel: Long,
)