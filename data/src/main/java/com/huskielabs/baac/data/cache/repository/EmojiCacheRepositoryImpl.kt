package com.huskielabs.baac.data.cache.repository

import com.huskielabs.baac.data.cache.dao.EmojiDAO
import com.huskielabs.baac.data.cache.dbo.EmojiDBO
import com.huskielabs.baac.data.repository.EmojiCacheRepository
import javax.inject.Inject

class EmojiCacheRepositoryImpl @Inject constructor(
  private val emojiDao: EmojiDAO
) : EmojiCacheRepository {

  override suspend fun insertAll(emojis: List<EmojiDBO>) {
    emojiDao.insertAll(emojis)
  }

  override suspend fun getAll(): List<EmojiDBO> {
    return emojiDao.getAll()
  }

  override suspend fun getSize(): Int {
    return emojiDao.getSize()
  }

  override suspend fun getById(id: Int): EmojiDBO {
    return emojiDao.getById(id)
  }

}
