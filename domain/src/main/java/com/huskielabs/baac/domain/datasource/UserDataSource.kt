package com.huskielabs.baac.domain.datasource

import com.huskielabs.baac.domain.model.UserAvatarModel
import com.huskielabs.baac.domain.model.UserRepoModel
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

  suspend fun getUserAvatarByUserName(userName: String): String?

  suspend fun deleteUserAvatar(userAvatarModel: UserAvatarModel)

  fun getAll(): Flow<List<UserAvatarModel>>

  suspend fun getUserRepos(page: Int): List<UserRepoModel>

}
