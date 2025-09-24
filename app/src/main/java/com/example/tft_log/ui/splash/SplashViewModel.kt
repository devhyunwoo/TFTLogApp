package com.example.tft_log.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.tft_log.core.ApiResult
import com.example.tft_log.core.BaseViewModel
import com.example.tft_log.core.safeApiCall
import com.tft.log.data.repository.datastore.DatastoreRepository
import com.tft.log.data.repository.dragon.DragonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dragonRepository: DragonRepository,
    private val datastoreRepository: DatastoreRepository,
) : BaseViewModel<SplashContract.State, SplashContract.Event, SplashContract.Effect>(
    SplashContract.State(
        alpha = 1f
    )
) {
    override fun setEvent(event: SplashContract.Event) {}

    init {
        viewModelScope.launch {
            val setAlphaDeferred = async { setAlpha() }
            val updateDataDeferred = async { updateData() }

            awaitAll(setAlphaDeferred, updateDataDeferred)

            setEffect { SplashContract.Effect.NavigateToMain }
        }
    }

    private suspend fun setAlpha() {
        repeat(10) {
            delay(200)
            setState { copy(alpha = state.value.alpha + 0.1f) }
        }
    }

    private suspend fun updateData() {
        when (val result = safeApiCall { dragonRepository.getVersions() }) {
            is ApiResult.Success -> {

            }

            is ApiResult.Error -> {
                setEffect { SplashContract.Effect.ShowMessage(message = result.message) }
            }
        }
    }
}