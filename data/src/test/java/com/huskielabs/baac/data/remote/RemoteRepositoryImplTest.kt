package com.huskielabs.baac.data.remote

import com.huskielabs.baac.data.remote.dto.UserAvatarDTO
import com.huskielabs.baac.data.remote.dto.UserRepoDTO
import com.huskielabs.baac.data.remote.service.GitHubService
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.domain.model.UserRepoModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteRepositoryImplTest {

  private val githubService = mockk<GitHubService>()

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

  @Test
  fun `should get user repos`() {
    val page = 1
    val serviceResponse = listOf(UserRepoDTO("fullName", "url"))
    val expected = listOf(UserRepoDTO("fullName", "url"))

    coEvery { githubService.getUserRepos(page = page) } returns serviceResponse

    val actual = runBlocking { repository.getUserRepos(page) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { githubService.getUserRepos(page = page) }

    confirmVerified(githubService)
  }
}
