package com.example.tft_log.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.tft_log.ui.main.MainScreen

@Composable
fun TFTLogNavDisplay() {
    val backStack = remember { mutableStateListOf<Route>(Route.Main) }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Route.Main> {
                MainScreen()
            }
        }
    )
}