package com.example.tft_log.navigation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.tft_log.ui.main.MainScreen
import com.example.tft_log.ui.splash.SplashScreen

@SuppressLint("ContextCastToActivity")
@Composable
fun TFTLogNavDisplay(
    finish: () -> Unit,
) {
    val backStack = rememberNavBackStack(Route.Splash)
    val context = LocalContext.current.applicationContext

    var backPressedTime by remember { mutableLongStateOf(0L) }
    val backPressedInterval = 2000L


    BackHandler(backStack.size == 1 && backStack.last() == Route.Main) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < backPressedInterval) {
            finish()
        } else {
            backPressedTime = currentTime
            Toast.makeText(context, "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }

    }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = 500)
            ) togetherWith slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(durationMillis = 500)
            )
        },
        popTransitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            ) togetherWith slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        entryProvider = entryProvider {
            entry<Route.Main> {
                MainScreen(
                    showToast = { msg -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show() }
                )
            }

            entry<Route.Splash> {
                SplashScreen(
                    onNavigateToMain = {
                        backStack.add(Route.Main)
                        backStack.remove(Route.Splash)
                    },
                    showToast = { msg -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show() }
                )
            }
        }
    )
}