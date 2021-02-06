package com.huskielabs.baac.data.cache.repository

import com.huskielabs.baac.data.cache.dao.UserDAO
import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import com.huskielabs.baac.data.repository.CacheUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheUserRepositoryImpl @Inject constructor(
  private val userDao: UserDAO,
) : CacheUserRepository {

  override suspend fun insert(userAvatar: UserAvatarDBO) {
    userDao.insert(userAvatar)
  }

  override suspend fun getByUserName(userName: String): UserAvatarDBO? {
    return userDao.getByUserName(userName)
  }

  override fun getAll(): Flow<UserAvatarDBO> {
    return userDao.getAll()
  }

}