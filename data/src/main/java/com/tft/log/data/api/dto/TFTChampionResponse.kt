package com.tft.log.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TFTChampionResponse(
    @SerialName("type")
    val type: String,
    @SerialName("version")
    val version: String,
    @SerialName("data")
    val data: Map<String, TFTChampionDTO>
)

@Serializable
data class TFTChampionDTO(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("tier")
    val tier: Int,
    @SerialName("image")
    val image: ImageDTO
)

@Serializable
data class ImageDTO(
    @SerialName("full")
    val full: String
)
