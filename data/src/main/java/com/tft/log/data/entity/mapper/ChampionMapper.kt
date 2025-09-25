package com.tft.log.data.entity.mapper

import com.tft.log.data.api.dto.TFTChampionResponse
import com.tft.log.data.entity.ChampionEntity

fun TFTChampionResponse.toChampionEntity(): List<ChampionEntity> {
    return this.data.map {
        ChampionEntity(
            championId = it.value.id,
            imageName = it.value.image.full
        )
    }
}