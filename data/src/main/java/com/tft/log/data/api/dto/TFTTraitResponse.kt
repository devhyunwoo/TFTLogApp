package com.tft.log.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TFTTraitResponse(
    @SerialName("type")
    val type: String,
    @SerialName("version")
    val version: String,
    @SerialName("data")
    val data: Map<String, TFTTraitDTO>
)

@Serializable
data class TFTTraitDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val image: ImageDTO
)
