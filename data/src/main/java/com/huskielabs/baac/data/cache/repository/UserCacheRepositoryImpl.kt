package com.huskielabs.baac.data.cache.repository

import com.huskielabs.baac.data.cache.dao.UserDAO
import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import com.huskielabs.baac.data.repository.UserCacheRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserCacheRepositoryImpl @Inject constructor(
  private val userDao: UserDAO,
) : UserCacheRepository {

  override suspend fun insert(userAvatar: UserAvatarDBO) {
    userDao.insert(userAvatar)
  }

  override suspend fun getByUserName(userName: String): String? {
    return userDao.getByUserName(userName)
  }

  override fun getAll(): Flow<String> {
    return userDao.getAll()
  }

}