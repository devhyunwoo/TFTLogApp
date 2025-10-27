package com.example.tft_log.ui.main.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tft_log.R
import com.example.tft_log.ui.theme.AppColors

@Composable
fun FAB(
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(AppColors.Gray100.copy(0.4f), shape = CircleShape)
            .border(1.dp, AppColors.Gray600, CircleShape)
            .padding(5.dp)
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication= null ) { onClick() }
    ) {
        Icon(
            modifier = Modifier.size(35.dp),
            painter = painterResource(R.drawable.ic_prev_fab),
            contentDescription = "뒤로가기 버튼",
            tint = AppColors.PrimaryColor
        )
    }
}

@Preview
@Composable
fun FABPreview() {
    FAB()
}