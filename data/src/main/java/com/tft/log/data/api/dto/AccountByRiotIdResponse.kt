package com.tft.log.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class AccountByRiotIdResponse(
    @SerialName("puuid")
    val puuid: String,
)
