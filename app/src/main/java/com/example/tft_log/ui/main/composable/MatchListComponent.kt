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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.tft_log.R
import com.example.tft_log.ui.common.ChampionItem
import com.example.tft_log.ui.common.ChampionItemSize
import com.example.tft_log.ui.common.TraitItem
import com.example.tft_log.ui.common.TraitItemSize
import com.example.tft_log.ui.theme.AppColors
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.entity.Participant

fun LazyListScope.matchItemsComponent(
    matchItems: LazyPagingItems<MatchEntity>,
    onClickID: (Participant) -> Unit
) {
    items(count = matchItems.itemCount, key = { "$it - ${matchItems[it]?.gameDatetime}" }) { index ->
        val matchItem = matchItems[index]
        matchItem?.let {
            MatchItem(matchItem = matchItem, onClickID = onClickID)
        }
    }
}

@Composable
fun MatchItem(
    matchItem: MatchEntity,
    onClickID: (Participant) -> Unit
) {
    val bgColor = if (matchItem.me.win) AppColors.WinColor else AppColors.LoseColor
    var expanded by rememberSaveable(matchItem.me.puuid) { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .background(color = bgColor, shape = RoundedCornerShape(12.dp))
    ) {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            maxItemsInEachRow = 5,
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalArrangement = Arrangement.spacedBy(
                2.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            matchItem.me.traits.forEach { trait ->
                TraitItem(TraitItemSize.MEDIUM, trait = trait)
            }
        }
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
                    ChampionItem(size = ChampionItemSize.MEDIUM, unit = unit)
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
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = AppColors.Gray500
                )
                matchItem.participants.forEach { participant ->
                    ParticipantItem(participant = participant, onClickID = onClickID)
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
fun ParticipantItem(participant: Participant, onClickID: (Participant) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(color = AppColors.Gray200, shape = CircleShape)
                        .size(20.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = participant.rank.toString(),
                        fontSize = 10.sp,
                        color = AppColors.Black,
                        fontWeight = FontWeight.W500
                    )
                }
                Text(
                    modifier = Modifier.clickable {
                        onClickID(participant)
                    },
                    text = participant.id,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    textDecoration = TextDecoration.Underline,
                    color = AppColors.Black
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = participant.lastRound,
                    fontSize = 10.sp,
                    color = AppColors.Black,
                    fontWeight = FontWeight.W700
                )
                Text(
                    text = participant.datetime,
                    fontSize = 10.sp,
                    color = AppColors.Gray900,
                    fontWeight = FontWeight.W500
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                participant.traits.forEach { trait ->
                    TraitItem(size = TraitItemSize.SMALL, trait = trait)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_coin),
                        contentDescription = "코인",
                        modifier = Modifier.size(15.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = participant.goldLeft.toString(),
                        fontSize = 10.sp,
                        color = AppColors.Black,
                        fontWeight = FontWeight.W700
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_hammer),
                        contentDescription = "딜량",
                        modifier = Modifier.size(15.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = participant.totalDamage.toString(),
                        fontSize = 10.sp,
                        color = AppColors.Gray900,
                        fontWeight = FontWeight.W500
                    )
                }
            }
        }
        LazyRow {
            itemsIndexed(items = participant.units, key = { i, _ -> "champion_$i" }) { _, unit ->
                ChampionItem(size = ChampionItemSize.SMALL, unit = unit)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParticipantItemPreview() {
    ParticipantItem(
        participant = Participant(
            5000, "9-1", 5, 2, "puuid", "id", "17:42", true, 4000, listOf(), listOf()
        ), {}
    )
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
                lastRound = "9-1",
                level = 8,
                rank = 1,
                puuid = "sample-puuid",
                id = "sample-id",
                datetime = "3분",
                win = true,
                totalDamage = 5000,
                units = emptyList(),
                traits = emptyList()
            ),
            participants = emptyList(),
            gameVersion = "13.12.1"
        ),
        {}
    )
}