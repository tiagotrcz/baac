package com.huskielabs.baac.data.cache.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_avatar")
data class UserAvatarDBO(
  @PrimaryKey
  @ColumnInfo(name = "user_name")
  val userName: String,
  @ColumnInfo(name = "avatar_url")
  val avatarUrl: String,
)
