package com.tft.log.data.entity.mapper

import com.tft.log.data.api.dto.TFTTraitResponse
import com.tft.log.data.entity.TraitEntity

fun TFTTraitResponse.toTraitMapper(): List<TraitEntity> {
    return this.data.map {
        TraitEntity(
            traitId = it.value.id.lowercase(),
            imageName = it.value.image.full
        )
    }
}