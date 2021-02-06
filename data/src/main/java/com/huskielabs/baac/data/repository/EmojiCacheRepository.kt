package com.huskielabs.baac.data.repository

import com.huskielabs.baac.data.cache.dbo.EmojiDBO

interface EmojiCacheRepository {

  suspend fun insertAll(emojis: List<EmojiDBO>)

  suspend fun getAll(): List<EmojiDBO>

  suspend fun getSize(): Int

  suspend fun getById(id: Int): EmojiDBO

}