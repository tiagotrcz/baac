package com.huskielabs.baac.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDAO: BaseDAO<UserAvatarDBO>() {

  @Query("SELECT avatar_url FROM user_avatar WHERE user_name = :userName")
  abstract suspend fun getByUserName(userName: String): String?

  @Query("SELECT avatar_url FROM user_avatar")
  abstract fun getAll(): Flow<String>

}