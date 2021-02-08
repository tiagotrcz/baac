package com.huskielabs.baac.data.remote

import com.huskielabs.baac.data.remote.dto.UserRepoDTO
import com.huskielabs.baac.data.remote.service.GitHubService
import com.huskielabs.baac.data.repository.RemoteRepository
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

  override suspend fun getUserRepo(userName: String, page: Int, perPage: Int): List<UserRepoDTO> {
    return gitHubService.getUserRepo(userName, page, perPage)
  }

}
