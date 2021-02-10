package com.huskielabs.baac.data.remote.dto

import com.squareup.moshi.Json

data class UserAvatarDTO(
  @Json(name = "avatar_url")
  val avatarUrl: String,
)
