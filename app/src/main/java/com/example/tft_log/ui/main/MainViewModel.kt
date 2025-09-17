package com.example.tft_log.ui.main

import androidx.lifecycle.viewModelScope
import com.example.tft_log.core.ApiResult
import com.example.tft_log.core.BaseViewModel
import com.example.tft_log.core.safeApiCall
import com.tft.log.data.repository.RiotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val riotRepository: RiotRepository
) : BaseViewModel<MainContract.State, MainContract.Event, MainContract.Effect>(
    MainContract.State(
        textField = ""
    )
) {
    override fun setEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.OnClickSearch -> {
                event.also {
                    when {
                        it.text.isEmpty() -> {
                            setInvalidInput()
                            return
                        }

                        !it.text.contains("#") -> {
                            setInvalidInput()
                            return
                        }

                        it.text.split("#").size != 2 -> {
                            setInvalidInput()
                            return
                        }
                        it.text.split("#")[0].isEmpty() -> {
                            setInvalidInput()
                            return
                        }
                        it.text.split("#")[1].isEmpty() -> {
                            setInvalidInput()
                            return
                        }
                    }
                }
                val (gameName, tagLine) = event.text.split("#")
                viewModelScope.launch {
                    getAccountByRiotId(gameName = gameName, tagLine = tagLine)
                }
            }
        }
    }


    private fun setInvalidInput() {
        setEffect {
            MainContract.Effect.ShowErrorMessage("소환사명#태그라인 형식으로 입력해주세요.")
        }
    }
    suspend fun getAccountByRiotId(gameName: String, tagLine: String) {
        when (val result = safeApiCall {
            riotRepository.getAccountByRiotId(
                gameName = gameName,
                tagLine = tagLine
            )
        }) {
            is ApiResult.Success -> {
                setEffect {
                    MainContract.Effect.ShowErrorMessage("성공: ${result.data.puuid}")
                }
            }

            is ApiResult.Error -> {
                setEffect {
                    MainContract.Effect.ShowErrorMessage(result.message)
                }
            }
        }
    }
}