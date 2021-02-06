package com.huskielabs.baac.data.remote

import com.huskielabs.baac.data.datasource.RemoteDataSource
import com.huskielabs.baac.data.remote.service.GithubService

class RemoteDataSourceImpl(private val githubService: GithubService) : RemoteDataSource {

  override suspend fun getEmojis(): List<String> {
    return githubService.getEmojis().values.toList()
  }

  override suspend fun getUserAvatarUrl(): String {
    return githubService.getUserAvatar().avatarUrl
  }

}