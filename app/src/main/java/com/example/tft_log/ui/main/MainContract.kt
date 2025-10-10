package com.example.tft_log.ui.main

import com.example.tft_log.core.UiEffect
import com.example.tft_log.core.UiEvent
import com.example.tft_log.core.UiState
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.entity.Participant

sealed interface MainContract {
    data class State(
        val matchItems: List<MatchEntity>?,
        val initialText: String,
        val isLoading : Boolean,
    ) : UiState

    sealed class Event : UiEvent {
        data class OnClickSearch(val text: String) : Event()

        data class OnClickID(val participant: Participant) : Event()
    }


    sealed class Effect : UiEffect {
        data class ShowErrorMessage(val message: String) : Effect()
    }
}