package com.huskielabs.baac.data.cache.repository

import com.huskielabs.baac.data.cache.dao.EmojiDAO
import com.huskielabs.baac.data.cache.dbo.EmojiDBO
import com.huskielabs.baac.data.repository.CacheEmojiRepository
import javax.inject.Inject

class CacheEmojiRepositoryImpl @Inject constructor(
  private val emojiDao: EmojiDAO
) : CacheEmojiRepository {

  override suspend fun insertAll(emojis: List<EmojiDBO>) {
    emojiDao.insertAll(emojis)
  }

  override suspend fun getAll(): List<EmojiDBO> {
    return emojiDao.getAll()
  }

}