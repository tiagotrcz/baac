package com.huskielabs.baac.data

import com.huskielabs.baac.data.cache.dbo.EmojiDBO
import com.huskielabs.baac.data.repository.EmojiCacheRepository
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.domain.datasource.EmojiDataSource
import javax.inject.Inject
import kotlin.random.Random

class EmojiDataSourceImpl @Inject constructor(
  private val remoteRepository: RemoteRepository,
  private val emojiCacheRepository: EmojiCacheRepository,
) : EmojiDataSource {

  override suspend fun getRandomEmoji(): String {
    val emojiCount = emojiCacheRepository.getSize()

    if (emojiCount == 0) fetchAndSaveEmojisToDatabase()

    val randomId = Random.nextInt(1, emojiCount)
    val emoji = emojiCacheRepository.getById(randomId)

    return emoji.url
  }

  override suspend fun getAll(): List<String> {
    val emojiCount = emojiCacheRepository.getSize()

    if (emojiCount == 0) fetchAndSaveEmojisToDatabase()

    return emojiCacheRepository.getAll().map { it.url }
  }

  private suspend fun fetchAndSaveEmojisToDatabase() {
    val emojis = remoteRepository.getEmojis()
    emojiCacheRepository.insertAll(emojis.map { emojiUrl -> EmojiDBO(url = emojiUrl) })
  }

}