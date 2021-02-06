package com.huskielabs.baac.usecase

import com.huskielabs.baac.domain.datasource.EmojiDataSource
import com.huskielabs.baac.domain.usecase.GetRandomEmojiUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetRandomEmojiUseCaseTest {

  private val emojiDataSource = mockk<EmojiDataSource>()

  private lateinit var useCase: GetRandomEmojiUseCase

  @Before
  fun `set up`() {
    useCase = GetRandomEmojiUseCase(emojiDataSource)
  }

  @Test
  fun `should get ramdon emoji`() {
    val dataSourceResponse = "emojiUrl"
    val expected = "emojiUrl"

    coEvery { emojiDataSource.getRandomEmoji() } returns dataSourceResponse

    val actual = runBlocking { useCase(NoParams) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { emojiDataSource.getRandomEmoji() }

    confirmVerified(emojiDataSource)
  }

}