package com.example.tft_log.ui.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tft_log.ui.theme.AppColors
import com.tft.log.data.entitiy.MatchEntity

fun LazyListScope.matchItemsComponent(
    matchItems: List<MatchEntity>
) {
    items(items = matchItems, key = { it.gameId }) { matchItem ->
        MatchItem(matchItem = matchItem)
    }
}

@Composable
fun MatchItem(
    matchItem: MatchEntity
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = AppColors.White),
        horizontalAlignment = Alignment.Start
    ) {
        Text(matchItem.gameId.toString())
        Text(matchItem.gameDatetime)
        Text(matchItem.gameLength)
    }
}