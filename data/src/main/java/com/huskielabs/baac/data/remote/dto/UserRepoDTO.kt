package com.huskielabs.baac.data.remote.dto

import com.squareup.moshi.Json

data class UserRepoDTO(
  @Json(name = "full_name")
  val fullName: String,
  @Json(name = "html_url")
  val url: String,
)
