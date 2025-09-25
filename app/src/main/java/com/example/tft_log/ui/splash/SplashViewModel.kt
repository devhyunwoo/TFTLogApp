package com.example.tft_log.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.tft_log.core.BaseViewModel
import com.tft.log.data.entity.mapper.toChampionEntity
import com.tft.log.data.repository.datastore.DatastoreRepository
import com.tft.log.data.repository.dp.DatabaseRepository
import com.tft.log.data.repository.dragon.DragonRepository
import com.tft.log.data.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dragonRepository: DragonRepository,
    private val datastoreRepository: DatastoreRepository,
    private val db: DatabaseRepository
) : BaseViewModel<SplashContract.State, SplashContract.Event, SplashContract.Effect>(
    SplashContract.State(
        dummy = ""
    )
) {
    override fun setEvent(event: SplashContract.Event) {}

    init {
        viewModelScope.launch {
            val delayDeferred = async { delay(1500) }
            val deferred = async { getLatestVersion() }
            awaitAll(deferred, delayDeferred)
            setEffect { SplashContract.Effect.NavigateToMain }
        }
    }

    private suspend fun getLatestVersion() = coroutineScope {
        when (val result = dragonRepository.getVersions()) {
            is ApiResult.Success -> {
                val localLatestVersion: Float =
                    datastoreRepository.getLatestVersion().first().toFloatOrNull() ?: 0.0f
                val latestVersion: Float = result.data.first().toFloatOrNull() ?: 0.0f

                if (latestVersion > localLatestVersion) {
                    val version = latestVersion.toString()
                    datastoreRepository.setLatestVersion(version = version)
                    val deferred = result.data.take(10).map { version ->
                        async { getChampion(version) }
                    }
                    deferred.awaitAll()
                }
            }

            is ApiResult.Error -> {
                setEffect { SplashContract.Effect.ShowMessage(message = result.message) }
            }
        }
    }

    private suspend fun getChampion(version: String) {
        when (val result = dragonRepository.getChampions(version = version)) {
            is ApiResult.Success -> {
                db.setChampionEntities(championEntities = result.data.toChampionEntity())
            }

            is ApiResult.Error -> {
                setEffect { SplashContract.Effect.ShowMessage(message = result.message) }
            }
        }
    }
}