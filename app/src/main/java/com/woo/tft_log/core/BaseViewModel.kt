package com.woo.tft_log.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, Event : UiEvent, Effect : UiEffect>(
    initialValue: State
) : ViewModel() {
    protected val TAG : String = this::class.java.simpleName
    private val _state: MutableStateFlow<State> = MutableStateFlow(initialValue)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect: Channel<Effect> = Channel(capacity = Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    abstract fun setEvent(event: Event)

    protected fun setEffect(effect: () -> Effect) {
        val newEffect = effect()
        viewModelScope.launch {
            _effect.send(newEffect)
        }
    }

    protected fun setState(reduce: State.() -> State) {
        val newState = reduce(_state.value)
        _state.update { newState }
    }
}
