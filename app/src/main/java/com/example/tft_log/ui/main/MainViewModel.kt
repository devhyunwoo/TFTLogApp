package com.example.tft_log.ui.main

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tft_log.core.BaseViewModel
import com.tft.log.data.entity.MatchEntity
import com.tft.log.data.repository.db.DatabaseRepository
import com.tft.log.data.repository.paging.PagingRepository
import com.tft.log.data.repository.riot.RiotRepository
import com.tft.log.data.repository.tft.TftRepository
import com.tft.log.data.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val riotRepository: RiotRepository,
    private val pagingRepository: PagingRepository,
    private val tftRepository: TftRepository,
    private val db: DatabaseRepository
) : BaseViewModel<MainContract.State, MainContract.Event, MainContract.Effect>(
    MainContract.State(
        initialText = "",
        hasSearch = false,
        userEntity = null,
    )
) {
    private val _puuid = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val matchListFlow: Flow<PagingData<MatchEntity>> = _puuid.filterNotNull().flatMapLatest {
        pagingRepository.getMatchPagingData(it)
    }.cachedIn(viewModelScope)

    val recentUserEntities = db.getUserEntities()
        ?.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

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

            is MainContract.Event.OnClickID -> {
                setState {
                    copy(
                        initialText = event.participant.id.replace(" ", "")
                    )
                }
                setEffect { MainContract.Effect.AnimateScrollToTop }
                with(event.participant.puuid) {
                    _puuid.value = this
                    viewModelScope.launch {
                        getUserEntity(puuid = this@with)
                    }
                }
            }

            MainContract.Event.OnClickFAB -> {
                setState {
                    copy(
                        initialText = "",
                        hasSearch = false,
                        userEntity = null
                    )
                }
                _puuid.value = ""
            }
        }
    }


    private fun setInvalidInput() {
        setEffect {
            MainContract.Effect.ShowErrorMessage("소환사명#태그라인 형식으로 입력해주세요.")
        }
    }

    suspend fun getAccountByRiotId(gameName: String, tagLine: String) {
        when (val result = riotRepository.getAccountByRiotId(
            gameName = gameName, tagLine = tagLine
        )) {
            is ApiResult.Success -> {
                setState {
                    copy(
                        hasSearch = true,
                        initialText = "${result.data.gameName}#${result.data.tagLine}"
                    )
                }
                setEffect { MainContract.Effect.AnimateScrollToTop }
                with(result.data.puuid) {
                    _puuid.value = this
                    getUserEntity(puuid = this)
                }
            }

            is ApiResult.Error -> {
                setEffect {
                    MainContract.Effect.ShowErrorMessage(result.message)
                }
            }
        }
    }

    suspend fun getUserEntity(puuid: String) {
        val userEntity = tftRepository.getUserEntity(puuid = puuid)
        userEntity?.let {
            db.setUserEntity(userEntity = userEntity)
            setState {
                copy(
                    userEntity = userEntity
                )
            }
        }
    }
}