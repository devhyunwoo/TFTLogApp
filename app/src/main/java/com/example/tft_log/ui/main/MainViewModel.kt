package com.example.tft_log.ui.main

import androidx.lifecycle.viewModelScope
import com.example.tft_log.core.ApiResult
import com.example.tft_log.core.BaseViewModel
import com.example.tft_log.core.safeApiCall
import com.example.tft_log.ui.main.mapper.toMatchEntity
import com.tft.log.data.repository.riot.RiotRepository
import com.tft.log.data.repository.tft.TftRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val riotRepository: RiotRepository,
    private val tftRepository: TftRepository,
) : BaseViewModel<MainContract.State, MainContract.Event, MainContract.Effect>(
    MainContract.State(
        matchItems = null
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
                getMachIdsByPuuid(puuid = result.data.puuid)
            }

            is ApiResult.Error -> {
                setEffect {
                    MainContract.Effect.ShowErrorMessage(result.message)
                }
            }
        }
    }

    private suspend fun getMachIdsByPuuid(puuid: String) {
        when (val result = safeApiCall { tftRepository.getMatchIdsByPuuid(puuid = puuid) }) {
            is ApiResult.Success -> {
                supervisorScope {
                    result.data
                        .take(10)
                        .map { matchId -> async { getMatchByMatchId(matchId = matchId) } }
                        .awaitAll()
                }
            }

            is ApiResult.Error -> {
                setEffect {
                    MainContract.Effect.ShowErrorMessage(result.message)
                }
            }
        }
    }

    private suspend fun getMatchByMatchId(matchId: String) {
        when (val result = safeApiCall { tftRepository.getMatchByMatchId(matchId = matchId) }) {
            is ApiResult.Success -> {
                setState {
                    copy(
                        matchItems = this.matchItems?.plus(result.data.toMatchEntity()) ?: listOf(
                            result.data.toMatchEntity()
                        )
                    )
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