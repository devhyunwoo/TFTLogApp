package com.example.tft_log.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.tft_log.utils.ColorUtils.getTraitColor
import com.tft.log.data.entity.Trait

@Composable
fun TraitItem(size: TraitItemSize, trait: Trait) {
    Box(
        modifier = Modifier
            .background(color = getTraitColor(trait.style), shape = CircleShape)
            .padding(size.padding), contentAlignment = Alignment.Center
    ) {
        CustomAsyncImage(
            modifier = Modifier
                .size(size.traitSize)
                .clip(shape = RoundedCornerShape(12.dp)),
            model = trait.imageUrl,
            contentDescription = "특성",
            contentScale = ContentScale.Crop
        )
    }
}

enum class TraitItemSize(val traitSize: Dp, val padding: Dp) {
    SMALL(traitSize = 15.dp, padding = 3.dp),
    MEDIUM(traitSize = 30.dp, padding = 5.dp)
}