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

  @GET("users/{username}/repos")
  suspend fun getUserRepos(
    @Path("username") userName: String = "google",
    @Query("page") page: Int,
    @Query("per_page") perPage: Int = 30,
  ): List<UserRepoDTO>

}
