package com.tft.log.data.repository.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DatastoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DatastoreRepository {
    override suspend fun getLatestVersion(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[LATEST_VERSION_KEY] ?: ""
            }
    }

    override suspend fun setLatestVersion(version: String) {
        dataStore.edit { preferences ->
            preferences[LATEST_VERSION_KEY] = version
        }
    }


    companion object {
        const val LATEST_VERSION = "LATEST_VERSION"
        val LATEST_VERSION_KEY = stringPreferencesKey(LATEST_VERSION)
    }
}