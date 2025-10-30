package com.woo.tft_log.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woo.tft_log.R

@Composable
fun LoadingView() {
    Lottie(
        lottie = R.raw.loading_lottie,
        modifier = Modifier.size(100.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LoadingViewPreview() {
    LoadingView()
}