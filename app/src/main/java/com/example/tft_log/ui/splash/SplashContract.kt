package com.example.tft_log.ui.splash

import com.example.tft_log.core.UiEffect
import com.example.tft_log.core.UiEvent
import com.example.tft_log.core.UiState

sealed interface SplashContract {
    data class State(
        val alpha: Float
    ) : UiState

    sealed class Event : UiEvent

    sealed class Effect : UiEffect {
        data object NavigateToMain : Effect()

        data class ShowMessage(val message: String) : Effect()
    }
}