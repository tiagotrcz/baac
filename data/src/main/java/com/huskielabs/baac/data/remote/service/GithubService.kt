package com.huskielabs.baac.data.remote.service

import com.huskielabs.baac.data.remote.dto.UserAvatarDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

  @GET("emojis")
  suspend fun getEmojis(): Map<String, String>

  @GET("users/{username}")
  suspend fun getUserAvatar(@Path("userName") userName: String): UserAvatarDTO

}
