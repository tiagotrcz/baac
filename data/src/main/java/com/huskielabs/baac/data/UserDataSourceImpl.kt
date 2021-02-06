package com.huskielabs.baac.data

import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.data.repository.UserCacheRepository
import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
  private val remoteRepository: RemoteRepository,
  private val userCacheRepository: UserCacheRepository,
) : UserDataSource {

  override suspend fun insert(user: UserModel) {
    userCacheRepository.insert(
      UserAvatarDBO(
        userName = user.userName,
        avatarUrl = user.avatarUrl,
      )
    )
  }

  override suspend fun getUserAvatarByUserName(userName: String): String {
    return userCacheRepository.getByUserName(userName) ?: remoteRepository.getUserAvatarUrl()
      .also { avatar ->
        userCacheRepository.insert(
          UserAvatarDBO(
            userName = userName,
            avatarUrl = avatar,
          )
        )
      }
  }

  override fun getAll(): Flow<String> {
    return userCacheRepository.getAll()
  }

}