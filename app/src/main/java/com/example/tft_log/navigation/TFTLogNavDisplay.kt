package com.example.tft_log.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.runtime.rememberSceneSetupNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.tft_log.ui.main.MainScreen
import com.example.tft_log.ui.splash.SplashScreen

@Composable
fun TFTLogNavDisplay() {
    val backStack = rememberSaveable { mutableStateListOf<Route>(Route.Splash) }
    val context = LocalContext.current.applicationContext

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(), rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
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