package com.huskielabs.baac.data.remote

import com.huskielabs.baac.data.remote.dto.UserAvatarDTO
import com.huskielabs.baac.data.remote.service.GithubService
import com.huskielabs.baac.data.repository.RemoteRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteRepositoryImplTest {

  private val githubService = mockk<GithubService>()

  private lateinit var repository: RemoteRepository

  @Before
  fun `set up`() {
    repository = RemoteRepositoryImpl(githubService)
  }

  @Test
  fun `should get emojis`() {
    val serviceResponse = mapOf("id" to "emoji")
    val expected = listOf("emoji")

    coEvery { githubService.getEmojis() } returns serviceResponse

    val actual = runBlocking { repository.getEmojis() }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { githubService.getEmojis() }

    confirmVerified(githubService)
  }

  @Test
  fun `should get user avatar url`() {
    val userName = "userName"
    val serviceResponse = UserAvatarDTO("avatarUrl")
    val expected = "avatarUrl"

    coEvery { githubService.getUserAvatar(userName) } returns serviceResponse

    val actual = runBlocking { repository.getUserAvatarUrl(userName) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { githubService.getUserAvatar(userName) }

    confirmVerified(githubService)
  }
}
