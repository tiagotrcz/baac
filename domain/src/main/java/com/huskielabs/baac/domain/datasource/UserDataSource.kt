package com.huskielabs.baac.domain.datasource

import com.huskielabs.baac.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

  suspend fun insert(user: UserModel)

  suspend fun getUserAvatarByUserName(userName: String): String?

  fun getAll(): Flow<String>

}