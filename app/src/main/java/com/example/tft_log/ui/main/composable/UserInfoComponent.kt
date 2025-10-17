package com.example.tft_log.ui.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tft_log.ui.common.CustomAsyncImage
import com.example.tft_log.ui.theme.AppColors
import com.tft.log.data.entity.UserEntity

fun LazyListScope.userInfoComponent(
    userEntity: UserEntity,
) {
    item {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(color = AppColors.Gray100, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    CustomAsyncImage(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = CircleShape)
                            .border(1.dp, color = AppColors.Black, shape = CircleShape)
                            .align(Alignment.Center),
                        model = userEntity.profileImage.orEmpty(),
                        contentDescription = "Profile Image"
                    )
                    Text(
                        modifier = Modifier
                            .background(
                                color = AppColors.Black,
                                shape = RoundedCornerShape(999.dp)
                            )
                            .padding(horizontal = 5.dp)
                            .align(Alignment.BottomCenter),
                        text = userEntity.level.orEmpty(),
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.W600,
                        color = AppColors.White
                    )
                }
                Text(
                    text = userEntity.nickname.orEmpty(),
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight.W700,
                    color = AppColors.Black
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = userEntity.tier.orEmpty(),
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.W500,
                        color = AppColors.Black
                    )
                    Text(
                        text = userEntity.lp.orEmpty(),
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.W500,
                        color = AppColors.Black
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = userEntity.wins.orEmpty(),
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.W500,
                        color = AppColors.Black
                    )
                    Text(
                        text = userEntity.losses.orEmpty(),
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.W500,
                        color = AppColors.Black
                    )
                    Text(
                        text = userEntity.winRate.orEmpty(),
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.W500,
                        color = AppColors.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfoComponentPreview() {
    val userEntity = UserEntity(
        puuid = "sample-puuid",
        nickname = "SampleUser#KR",
        level = "50",
        profileImage = "https://example.com/profile.jpg",
        tier = "골드 II",
        lp = "75 LP",
        wins = "120승",
        losses = "80패",
        winRate = "60.0%"
    )
    LazyColumn(
        modifier = Modifier
            .background(color = AppColors.PrimaryColor)
            .padding(10.dp)
    ) {
        userInfoComponent(userEntity = userEntity)
    }
}