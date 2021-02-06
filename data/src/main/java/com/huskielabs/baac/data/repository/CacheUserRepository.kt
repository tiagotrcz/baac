package com.huskielabs.baac.data.repository

import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import kotlinx.coroutines.flow.Flow

interface CacheUserRepository {

  suspend fun insert(userAvatar: UserAvatarDBO)

  suspend fun getByUserName(userName: String): UserAvatarDBO?

  fun getAll(): Flow<UserAvatarDBO>

}