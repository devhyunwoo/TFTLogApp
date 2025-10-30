package com.woo.tft_log.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.woo.tft_log.ui.theme.AppColors

@Composable
fun CustomAsyncImage(
    modifier: Modifier,
    model: String,
    contentDescription: String,
    contentScale: ContentScale = ContentScale.Fit,
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(model)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .crossfade(false)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale
    ) {
        val state = painter.state
        when (state) {
            is AsyncImagePainter.State.Error -> {
                Box(contentAlignment = Alignment.Center) {
                    Text("ERROR", fontSize = 5.sp, color = AppColors.Red)
                }
            }

            is AsyncImagePainter.State.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(5.dp)) {
                    CircularProgressIndicator(color = AppColors.White, strokeWidth = 1.dp)
                }
            }

            is AsyncImagePainter.State.Success -> {
                SubcomposeAsyncImageContent()
            }

            else -> {}
        }
    }
}