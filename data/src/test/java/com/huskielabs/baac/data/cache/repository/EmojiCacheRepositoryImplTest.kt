package com.huskielabs.baac.data.cache.repository

import com.huskielabs.baac.data.cache.dao.EmojiDAO
import com.huskielabs.baac.data.cache.dbo.EmojiDBO
import com.huskielabs.baac.data.repository.EmojiCacheRepository
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

class EmojiCacheRepositoryImplTest {

  private val emojiDao = mockk<EmojiDAO>()

  private lateinit var repository: EmojiCacheRepository

  @Before
  fun `set up`() {
    repository = EmojiCacheRepositoryImpl(emojiDao)
  }

  @Test
  fun `should insert emoji list`() {
    val emojiList = listOf(EmojiDBO(url = "emojiUrl"))

    coEvery { emojiDao.insertAll(emojiList) } just runs

    runBlocking { repository.insertAll(emojiList) }

    coVerify(exactly = 1) { emojiDao.insertAll(emojiList) }

    confirmVerified(emojiDao)
  }

  @Test
  fun getAll() {
    val daoResponse = listOf(EmojiDBO(url = "emojiUrl"))
    val expected = listOf(EmojiDBO(url = "emojiUrl"))

    coEvery { emojiDao.getAll() } returns daoResponse

    val actual = runBlocking { repository.getAll() }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { emojiDao.getAll() }

    confirmVerified(emojiDao)
  }

  @Test
  fun getSize() {
    val daoResponse = 1
    val expected = 1

    coEvery { emojiDao.getSize() } returns daoResponse

    val actual = runBlocking { repository.getSize() }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { emojiDao.getSize() }

    confirmVerified(emojiDao)
  }

  @Test
  fun getById() {
    val daoResponse = EmojiDBO(url = "emojiUrl")
    val expected = EmojiDBO(url = "emojiUrl")
    val avatarId = 1

    coEvery { emojiDao.getById(avatarId) } returns daoResponse

    val actual = runBlocking { repository.getById(avatarId) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { emojiDao.getById(avatarId) }

    confirmVerified(emojiDao)
  }

}
