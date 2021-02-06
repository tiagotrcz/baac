package com.huskielabs.baac.data.remote

import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.data.remote.service.GithubService
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
  private val githubService: GithubService
) : RemoteRepository {

  override suspend fun getEmojis(): List<String> {
    return githubService.getEmojis().values.toList()
  }

  override suspend fun getUserAvatarUrl(): String {
    return githubService.getUserAvatar().avatarUrl
  }

}