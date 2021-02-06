package com.huskielabs.baac.data.cache.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emoji")
data class EmojiDBO(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "uid")
  val id: Int,
  @ColumnInfo(name = "url")
  val url: String,
)
