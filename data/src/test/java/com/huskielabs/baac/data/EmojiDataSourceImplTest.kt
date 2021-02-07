package com.huskielabs.baac.data

import com.huskielabs.baac.data.cache.dbo.EmojiDBO
import com.huskielabs.baac.data.repository.EmojiCacheRepository
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.domain.datasource.EmojiDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EmojiDataSourceImplTest {

  private val remoteRepository = mockk<RemoteRepository>()
  private val emojiCacheRepository = mockk<EmojiCacheRepository>()

  private lateinit var dataSource: EmojiDataSource

  @Before
  fun `set up`() {
    dataSource = EmojiDataSourceImpl(remoteRepository, emojiCacheRepository)
  }

  @Test
  fun `should get random emoji`() {
    val cacheResponseSize = 1
    val cacheResponseEmoji = EmojiDBO(id = 1, url = "emojiUrl")
    val id = 1
    val expected = "emojiUrl"

    coEvery { emojiCacheRepository.getSize() } returns cacheResponseSize
    coEvery { emojiCacheRepository.getById(id) } returns cacheResponseEmoji

    val actual = runBlocking { dataSource.getRandomEmoji() }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) {
      emojiCacheRepository.getSize()
      emojiCacheRepository.getById(id)
    }

    confirmVerified(remoteRepository, emojiCacheRepository)
  }

  @Test
  fun `should fetch emojis from remote and save then to cache and then get random emoji `() {
    val cacheResponseSize = 0
    val cacheResponseEmoji = EmojiDBO(id = 1, url = "emojiUrl")
    val remoteResponse = listOf("emojiUrl")
    val emojiDboList = listOf(EmojiDBO(url = "emojiUrl"))
    val id = 1
    val expected = "emojiUrl"

    coEvery { remoteRepository.getEmojis() } returns remoteResponse
    coEvery { emojiCacheRepository.insertAll(emojiDboList) } just runs
    coEvery { emojiCacheRepository.getSize() } returns cacheResponseSize
    coEvery { emojiCacheRepository.getById(id) } returns cacheResponseEmoji

    val actual = runBlocking { dataSource.getRandomEmoji() }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) {
      remoteRepository.getEmojis()
      emojiCacheRepository.insertAll(emojiDboList)
      emojiCacheRepository.getSize()
      emojiCacheRepository.getById(id)
    }

    confirmVerified(remoteRepository, emojiCacheRepository)
  }

  @Test
  fun `should get all emojis`() {
    val cacheResponseSize = 1
    val cacheResponseEmojiList = listOf(EmojiDBO(id = 1, url = "emojiUrl"))
    val expected = listOf("emojiUrl")

    coEvery { emojiCacheRepository.getSize() } returns cacheResponseSize
    coEvery { emojiCacheRepository.getAll() } returns cacheResponseEmojiList

    val actual = runBlocking { dataSource.getAll() }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) {
      emojiCacheRepository.getSize()
      emojiCacheRepository.getAll()
    }

    confirmVerified(remoteRepository, emojiCacheRepository)
  }
}
