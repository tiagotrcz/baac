package com.huskielabs.baac.domain.datasource

import kotlinx.coroutines.flow.Flow

interface UserDataSource {

  suspend fun getUserAvatarByUserName(userName: String): String?

  fun getAll(): Flow<List<String>>

}
