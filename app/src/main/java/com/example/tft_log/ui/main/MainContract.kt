package com.example.tft_log.ui.main

import com.example.tft_log.core.UiEffect
import com.example.tft_log.core.UiEvent
import com.example.tft_log.core.UiState

sealed interface MainContract {
    data class State(
        val textField: String
    ) : UiState

    data object Event : UiEvent
    data object Effect : UiEffect
}