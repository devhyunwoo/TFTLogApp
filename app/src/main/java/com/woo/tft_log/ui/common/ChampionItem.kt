package com.woo.tft_log.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.woo.tft_log.R
import com.tft.log.data.entity.Unit

@Composable
fun ChampionItem(size: ChampionItemSize, unit: Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(size.padding)) {
            repeat(unit.tier) {
                Icon(
                    painter = painterResource(R.drawable.img_star),
                    modifier = Modifier.size(size.tierSize),
                    contentDescription = "티어",
                    tint = Color.Unspecified
                )
            }
        }
        CustomAsyncImage(
            modifier = Modifier
                .size(size.championSize)
                .clip(shape = RoundedCornerShape(12.dp)),
            model = unit.characterImageUrl,
            contentDescription = "캐릭터",
            contentScale = ContentScale.Crop
        )
        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            unit.itemsImageUrl.forEach { itemUrl ->
                CustomAsyncImage(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(size.itemSize),
                    model = itemUrl,
                    contentDescription = "아이템",
                )
            }
        }
    }
}

enum class ChampionItemSize(
    val championSize: Dp,
    val itemSize: Dp,
    val tierSize: Dp,
    val padding: Dp
) {
    SMALL(championSize = 30.dp, itemSize = 9.dp, tierSize = 6.dp, padding = 0.4.dp),
    MEDIUM(championSize = 50.dp, itemSize = 15.dp, tierSize = 10.dp, padding = 2.dp),
}