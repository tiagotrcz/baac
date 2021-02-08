package com.huskielabs.baac.data

import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.data.repository.UserCacheRepository
import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserAvatarModel
import com.huskielabs.baac.domain.model.UserRepoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

  override fun getAll(): Flow<List<UserAvatarModel>> {
    return userCacheRepository.getAll().map { avatars ->
      avatars.map { avatar ->
        UserAvatarModel(avatar.userName, avatar.avatarUrl)
      }
    }
  }

  override suspend fun deleteUserAvatar(userAvatarModel: UserAvatarModel) {
    userCacheRepository.deleteUserAvatar(
      UserAvatarDBO(
        userAvatarModel.userName,
        userAvatarModel.avatarUrl,
      )
    )
  }

  override suspend fun getUserRepo(userName: String, page: Int, perPage: Int): List<UserRepoModel> {
    return remoteRepository.getUserRepo(userName, page, perPage).map { repo ->
      UserRepoModel(
        fullName = repo.fullName,
        url = repo.url,
      )
    }
  }

}
