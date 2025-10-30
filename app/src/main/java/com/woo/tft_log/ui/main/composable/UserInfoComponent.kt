package com.woo.tft_log.ui.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woo.tft_log.ui.theme.AppColors
import com.tft.log.data.entity.UserEntity

fun LazyListScope.userInfoComponent(
    userEntity: UserEntity,
) {
    item {
        UserInfoItem(userEntity = userEntity)
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