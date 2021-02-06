package com.huskielabs.baac.data.datasource

interface RemoteDataSource {

  suspend fun getEmojis(): List<String>

  suspend fun getUserAvatarUrl(): String

}