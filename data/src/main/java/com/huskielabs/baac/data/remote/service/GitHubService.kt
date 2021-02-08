package com.huskielabs.baac.data.remote.service

import com.huskielabs.baac.data.remote.dto.UserAvatarDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

  @GET("emojis")
  suspend fun getEmojis(): Map<String, String>

  @GET("users/{username}")
  suspend fun getUserAvatar(@Path("username") userName: String): UserAvatarDTO

}
