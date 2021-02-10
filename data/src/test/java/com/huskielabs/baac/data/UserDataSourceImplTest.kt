package com.huskielabs.baac.data

import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import com.huskielabs.baac.data.remote.dto.UserRepoDTO
import com.huskielabs.baac.data.repository.RemoteRepository
import com.huskielabs.baac.data.repository.UserCacheRepository
import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserAvatarModel
import com.huskielabs.baac.domain.model.UserRepoModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserDataSourceImplTest {

  private val remoteRepository = mockk<RemoteRepository>()
  private val userCacheRepository = mockk<UserCacheRepository>()

  private lateinit var dataSource: UserDataSource

  @Before
  fun `set up`() {
    dataSource = UserDataSourceImpl(remoteRepository, userCacheRepository)
  }

  @Test
  fun `should get user avatar by name from cache`() {
    val userName = "userName"
    val cacheResponse = "avatarUrl"
    val expected = "avatarUrl"

    coEvery { userCacheRepository.getByUserName(userName) } returns cacheResponse

    val actual = runBlocking { dataSource.getUserAvatarByUserName(userName) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { userCacheRepository.getByUserName(userName) }

    confirmVerified(remoteRepository, userCacheRepository)
  }

  @Test
  fun `should get user avatar by name from remote and than save it into cache`() {
    val userName = "userName"
    val cacheResponse = null
    val remoteResponse = "avatarUrl"
    val userAvatarDbo = UserAvatarDBO("userName", "avatarUrl")
    val expected = "avatarUrl"

    coEvery { userCacheRepository.getByUserName(userName) } returns cacheResponse
    coEvery { remoteRepository.getUserAvatarUrl(userName) } returns remoteResponse
    coEvery { userCacheRepository.insert(userAvatarDbo) } just runs

    val actual = runBlocking { dataSource.getUserAvatarByUserName(userName) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) {
      userCacheRepository.getByUserName(userName)
      remoteRepository.getUserAvatarUrl(userName)
      userCacheRepository.insert(userAvatarDbo)
    }

    confirmVerified(remoteRepository, userCacheRepository)
  }

  @Test
  fun `should get all avatars from cache`() {
    val cacheResponse = flowOf(listOf(UserAvatarDBO("userName","avatarUrl")))
    val expected = listOf(UserAvatarModel("userName","avatarUrl"))

    coEvery { userCacheRepository.getAll() } returns cacheResponse

    runBlocking {
      dataSource.getAll().collect { actual ->
        Assert.assertEquals(expected, actual)
      }
    }

    coVerify(exactly = 1) { userCacheRepository.getAll() }

    confirmVerified(remoteRepository, userCacheRepository)
  }

  @Test
  fun `should get users repo from remote`() {
    val page = 1
    val remoteResponse = listOf(UserRepoDTO("userName","avatarUrl"))
    val expected = listOf(UserRepoModel("userName","avatarUrl"))

    coEvery { remoteRepository.getUserRepos(page) } returns remoteResponse

    val actual = runBlocking { dataSource.getUserRepos(page) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { remoteRepository.getUserRepos(page) }

    confirmVerified(remoteRepository, userCacheRepository)
  }
}
