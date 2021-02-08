package com.huskielabs.baac.data.remote.service

import com.huskielabs.baac.data.remote.dto.UserAvatarDTO
import com.huskielabs.baac.data.remote.dto.UserRepoDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

  @GET("emojis")
  suspend fun getEmojis(): Map<String, String>

  @GET("users/{username}")
  suspend fun getUserAvatar(@Path("username") userName: String): UserAvatarDTO

  @GET("users/{username}")
  suspend fun getUserRepo(
    @Path("username") userName: String,
    @Query("page") page: Int,
    @Query("per_page") perPage: Int,
  ): List<UserRepoDTO>

}
