package com.example.tft_log.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.tft_log.core.BaseViewModel
import com.tft.log.data.entity.mapper.Mapper.toChampionEntity
import com.tft.log.data.entity.mapper.Mapper.toTraitEntity
import com.tft.log.data.repository.datastore.DatastoreRepository
import com.tft.log.data.repository.dp.DatabaseRepository
import com.tft.log.data.repository.dragon.DragonRepository
import com.tft.log.data.utils.ApiResult
import com.tft.log.data.utils.CommonUtils.compareVersion
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
                val localLatestVersion: String =
                    datastoreRepository.getLatestVersion().first().ifEmpty { "0.0.0" }
                val latestVersion: String = result.data.first()
                if (localLatestVersion.compareVersion(latestVersion) < 0) {
                    val version = latestVersion
                    datastoreRepository.setLatestVersion(version = version)
                    val deferred = result.data.asSequence().filter {
                        (it.slice(0..1).toIntOrNull() ?: 0) > 12
                    }.map { version ->
                        async { getChampion(version) }
                        async { getTrait(version) }
                    }.toList()
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

    private suspend fun getTrait(version: String) {
        when (val result = dragonRepository.getTraits(version = version)) {
            is ApiResult.Success -> {
                db.setTraitEntities(result.data.toTraitEntity())
            }

            is ApiResult.Error -> {
                setEffect { SplashContract.Effect.ShowMessage(message = result.message) }
            }
        }
    }
}