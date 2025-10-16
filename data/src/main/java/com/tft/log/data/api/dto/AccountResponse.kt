package com.tft.log.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class AccountResponse(
    @SerialName("puuid")
    val puuid: String,
    @SerialName("gameName")
    val gameName: String,
    @SerialName("tagLine")
    val tagLine: String
)
