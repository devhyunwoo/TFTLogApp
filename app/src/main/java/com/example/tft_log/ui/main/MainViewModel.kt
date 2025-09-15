package com.example.tft_log.ui.main

import com.example.tft_log.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MainViewModel : BaseViewModel<MainContract.State, MainContract.Event, MainContract.Effect>(
    MainContract.State(
        textField = ""
    )
) {
    override fun setEvent(event: MainContract.Event) {

    }
}