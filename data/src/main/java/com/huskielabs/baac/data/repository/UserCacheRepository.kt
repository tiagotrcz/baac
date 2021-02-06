package com.huskielabs.baac.data.repository

import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import kotlinx.coroutines.flow.Flow

interface UserCacheRepository {

  suspend fun insert(userAvatar: UserAvatarDBO)

  suspend fun getByUserName(userName: String): String?

  fun getAll(): Flow<String>

}