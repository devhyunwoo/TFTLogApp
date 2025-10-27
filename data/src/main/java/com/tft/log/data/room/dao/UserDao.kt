package com.tft.log.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tft.log.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setUserEntity(userEntity: UserEntity)

    @Query("SELECT * FROM UserEntity ORDER BY createdAt DESC LIMIT 10")
    fun getUserEntities(): Flow<List<UserEntity>>
}