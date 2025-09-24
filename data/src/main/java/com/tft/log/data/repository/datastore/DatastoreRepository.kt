package com.tft.log.data.repository.datastore

import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    suspend fun getLatestVersion(): Flow<String>
    suspend fun setLatestVersion(version: String)
}