package com.huskielabs.baac.data.remote

import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.data.remote.service.GitHubService
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
  private val gitHubService: GitHubService
) : RemoteRepository {

  override suspend fun getEmojis(): List<String> {
    return gitHubService.getEmojis().values.toList()
  }

  override suspend fun getUserAvatarUrl(userName: String): String {
    return gitHubService.getUserAvatar(userName).avatarUrl
  }

}
