package com.huskielabs.baac.data.remote.service

import com.huskielabs.baac.data.remote.dto.UserAvatarDTO
import retrofit2.http.GET

interface GithubService {

  @GET("emojis")
  suspend fun getEmojis(): Map<String, String>

  @GET("users/{username}")
  suspend fun getUserAvatar(): UserAvatarDTO

}