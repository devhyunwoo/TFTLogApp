package com.example.tft_log.ui.main.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tft_log.R
import com.example.tft_log.ui.theme.AppColors
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.entity.Participant

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
    val bgColor = if (matchItem.me.win) AppColors.WinColor else AppColors.LoseColor
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(color = bgColor, shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = AppColors.LightGoldColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${matchItem.me.rank}등",
                    color = AppColors.SecondaryColor,
                    fontWeight = FontWeight.W700,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                )
            }
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    5.dp,
                    alignment = Alignment.CenterHorizontally
                ),
            ) {
                matchItem.me.units.forEach { unit ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            repeat(unit.tier) {
                                Icon(
                                    painter = painterResource(R.drawable.img_star),
                                    modifier = Modifier.size(10.dp),
                                    contentDescription = "티어",
                                    tint = Color.Unspecified
                                )
                            }
                        }
                        AsyncImage(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(shape = RoundedCornerShape(12.dp)),
                            model = unit.characterImageUrl,
                            contentDescription = "캐릭터",
                            contentScale = ContentScale.Crop
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            unit.itemsImageUrl.forEach { itemUrl ->
                                AsyncImage(
                                    modifier = Modifier
                                        .clip(shape = CircleShape)
                                        .size(15.dp),
                                    model = itemUrl,
                                    contentDescription = "아이템",
                                )
                            }
                        }
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = matchItem.gameDatetime,
                    fontSize = 14.sp,
                    color = AppColors.Gray700,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W500,
                )
                Text(
                    text = matchItem.me.datetime,
                    fontSize = 14.sp,
                    color = AppColors.Gray700,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W500,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(vertical = 5.dp)
        ) {
            if (expanded) {
                matchItem.participants.forEach { participant ->
                    ParticipantItem(participant = participant)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .clickable {
                        expanded = !expanded
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(if (expanded) R.drawable.ic_up else R.drawable.ic_down),
                    contentDescription = "열림 닫힘 아이콘",
                    tint = AppColors.Black
                )
            }
        }
    }
}

@Composable
fun ParticipantItem(participant: Participant) {
    Row(modifier = Modifier.fillMaxWidth()){
        Text(
            text = participant.id
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MatchItemPreview() {
    MatchItem(
        matchItem = MatchEntity(
            isGameEnd = true,
            gameId = 1234567890,
            gameDatetime = "3일전",
            gameLength = "25:30",
            me = Participant(
                goldLeft = 10,
                lastRound = 9,
                level = 8,
                rank = 1,
                puuid = "sample-puuid",
                id = "sample-id",
                datetime = "3분",
                win = true,
                totalDamage = 5000,
                units = emptyList()
            ),
            participants = emptyList(),
            gameVersion = "13.12.1"
        )
    )
}