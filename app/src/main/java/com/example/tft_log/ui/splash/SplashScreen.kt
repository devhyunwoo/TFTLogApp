package com.example.tft_log.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tft_log.R
import com.example.tft_log.ui.theme.AppColors

@Composable
fun SplashScreen(
    onNavigateToMain: () -> Unit,
    showToast: (String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val alphaAnim = remember { Animatable(1f) }

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SplashContract.Effect.NavigateToMain -> {
                    onNavigateToMain()
                }

                is SplashContract.Effect.ShowMessage -> {
                    showToast(effect.message)
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        alphaAnim.animateTo(
            targetValue = 0f,       // 0f = 완전히 투명
            animationSpec = tween(
                durationMillis = 2000 // 2초
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.PrimaryColor),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                painter = painterResource(R.drawable.tft_logo),
                contentDescription = "스플래시 로고",
                alpha = alphaAnim.value
            )
        }
    }
}