package com.huskielabs.baac.usecase

import com.huskielabs.baac.domain.datasource.EmojiDataSource
import com.huskielabs.baac.domain.usecase.GetAllEmojisUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAllEmojisUseCaseTest {

  private val emojiDataSource = mockk<EmojiDataSource>()

  private lateinit var useCase: GetAllEmojisUseCase

  @Before
  fun `set up`() {
    useCase = GetAllEmojisUseCase(emojiDataSource)
  }

  @Test
  fun `should get all emojis`() {
    val dataSourceResponse = listOf("emojiUrl")
    val expected = listOf("emojiUrl")

    coEvery { emojiDataSource.getAll() } returns dataSourceResponse

    val actual = runBlocking { useCase(NoParams) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { emojiDataSource.getAll() }

    confirmVerified(emojiDataSource)
  }

}