package com.huskielabs.baac.data.repository

interface RemoteRepository {

  suspend fun getEmojis(): List<String>

  suspend fun getUserAvatarUrl(userName: String): String

}
