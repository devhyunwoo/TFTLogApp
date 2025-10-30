package com.woo.tft_log.ui.splash

import com.woo.tft_log.core.UiEffect
import com.woo.tft_log.core.UiEvent
import com.woo.tft_log.core.UiState

sealed interface SplashContract {
    data class State(
        val dummy: String
    ) : UiState

    sealed class Event : UiEvent

    sealed class Effect : UiEffect {
        data object NavigateToMain : Effect()

        data class ShowMessage(val message: String) : Effect()
    }
}