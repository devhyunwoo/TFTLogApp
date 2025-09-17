package com.example.tft_log.ui.main

import com.example.tft_log.core.UiEffect
import com.example.tft_log.core.UiEvent
import com.example.tft_log.core.UiState

sealed interface MainContract {
    data class State(
        val textField: String,
    ) : UiState

    sealed class Event : UiEvent {
        data class OnClickSearch(val text: String) : Event()
    }

    sealed class Effect : UiEffect {
        data class ShowErrorMessage(val message: String) : Effect()
    }
}