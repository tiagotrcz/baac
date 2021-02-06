package com.huskielabs.baac.data.repository

import com.huskielabs.baac.data.cache.dbo.EmojiDBO

interface CacheEmojiRepository {

  suspend fun insertAll(emojis: List<EmojiDBO>)

  suspend fun getAll(): List<EmojiDBO>

}