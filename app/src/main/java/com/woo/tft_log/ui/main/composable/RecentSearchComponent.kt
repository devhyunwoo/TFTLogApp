package com.woo.tft_log.ui.main.composable

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.tft.log.data.entity.UserEntity

fun LazyListScope.recentSearchComponent(
    userEntities: List<UserEntity>?,
    onClickUserInfo: (String) -> Unit,
) {
    userEntities?.let {
        items(
            items = it,
            key = { item -> item.puuid },
            contentType = { item -> item }) { userEntity ->
            UserInfoItem(userEntity = userEntity, onClickUserInfo = onClickUserInfo)
        }
    }
}
