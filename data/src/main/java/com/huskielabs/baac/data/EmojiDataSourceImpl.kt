package com.huskielabs.baac.data

import com.huskielabs.baac.data.cache.dbo.EmojiDBO
import com.huskielabs.baac.data.repository.EmojiCacheRepository
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.domain.datasource.EmojiDataSource
import com.huskielabs.baac.domain.model.EmojiModel
import javax.inject.Inject
import kotlin.random.Random

class EmojiDataSourceImpl @Inject constructor(
  private val remoteRepository: RemoteRepository,
  private val emojiCacheRepository: EmojiCacheRepository,
) : EmojiDataSource {

  override suspend fun getRandomEmoji(): EmojiModel {
    val emojiCount = emojiCacheRepository.getSize()

    if (emojiCount == 0) fetchAndSaveEmojisToDatabase()

    val randomId = Random.nextInt(1, emojiCount)
    val emoji = emojiCacheRepository.getById(randomId)

    return EmojiModel(emojiUrl = emoji.url)
  }

  override suspend fun getAll(): List<EmojiModel> {
    val emojiCount = emojiCacheRepository.getSize()

    if (emojiCount == 0) fetchAndSaveEmojisToDatabase()

    return emojiCacheRepository.getAll().map { emoji -> EmojiModel(emojiUrl = emoji.url) }
  }

  private suspend fun fetchAndSaveEmojisToDatabase() {
    val emojis = remoteRepository.getEmojis()
    emojiCacheRepository.insertAll(emojis.map { emojiUrl -> EmojiDBO(url = emojiUrl) })
  }

}