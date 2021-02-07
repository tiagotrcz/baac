package com.huskielabs.baac.data

import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.data.repository.UserCacheRepository
import com.huskielabs.baac.domain.datasource.UserDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
  private val remoteRepository: RemoteRepository,
  private val userCacheRepository: UserCacheRepository,
) : UserDataSource {

  override suspend fun getUserAvatarByUserName(userName: String): String {
    return userCacheRepository.getByUserName(userName)
      ?: remoteRepository.getUserAvatarUrl(userName)
        .also { avatar ->
          userCacheRepository.insert(
            UserAvatarDBO(
              userName = userName,
              avatarUrl = avatar,
            )
          )
        }
  }

  override fun getAll(): Flow<List<String>> {
    return userCacheRepository.getAll()
  }

}
