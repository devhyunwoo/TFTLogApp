package com.example.tft_log.ui.main

import android.util.Log
import com.example.tft_log.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<MainContract.State, MainContract.Event, MainContract.Effect>(
        MainContract.State(
            textField = ""
        )
    ) {
    override fun setEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnClickSearch -> {
                Log.d(TAG, "Search: ${event.text}")
            }
        }
    }
}