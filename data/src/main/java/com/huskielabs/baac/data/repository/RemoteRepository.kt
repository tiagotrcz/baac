package com.huskielabs.baac.data.repository

import com.huskielabs.baac.data.remote.dto.UserRepoDTO

interface RemoteRepository {

  suspend fun getEmojis(): List<String>

  suspend fun getUserAvatarUrl(userName: String): String

  suspend fun getUserRepos(page: Int): List<UserRepoDTO>

}
