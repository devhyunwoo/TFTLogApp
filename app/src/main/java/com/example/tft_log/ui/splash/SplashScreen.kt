package com.example.tft_log.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.tft_log.R
import com.example.tft_log.ui.theme.AppColors

@Composable
fun SplashScreen(
    onNavigateToMain: () -> Unit,
    showToast: (String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
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

    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.PrimaryColor),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.tft_logo),
                contentDescription = "스플래시 로고",
                alpha = state.alpha
            )
        }
    }
}