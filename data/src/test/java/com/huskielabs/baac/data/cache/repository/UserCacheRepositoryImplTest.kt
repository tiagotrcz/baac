package com.huskielabs.baac.data.cache.repository

import com.huskielabs.baac.data.cache.dao.UserDAO
import com.huskielabs.baac.data.cache.dbo.UserAvatarDBO
import com.huskielabs.baac.data.repository.UserCacheRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserCacheRepositoryImplTest {

  private val userDao = mockk<UserDAO>()

  private lateinit var repository: UserCacheRepository

  @Before
  fun `set up`() {
    repository = UserCacheRepositoryImpl(userDao)
  }

  @Test
  fun `should insert user avatar`() {
    val userAvatar = UserAvatarDBO("userName", "avatarUrl")

    coEvery { userDao.insert(userAvatar) } returns INSERTED

    runBlocking { repository.insert(userAvatar) }

    coVerify(exactly = 1) { userDao.insert(userAvatar) }

    confirmVerified(userDao)
  }

  @Test
  fun `should get avatar by user name`() {
    val userName = "userName"
    val daoResponse = "avatarUrl"
    val expected = "avatarUrl"

    coEvery { userDao.getByUserName(userName) } returns daoResponse

    val actual = runBlocking { repository.getByUserName(userName) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { userDao.getByUserName(userName) }

    confirmVerified(userDao)
  }

  @Test
  fun `should get all avatars`() {
    val daoResponse = flowOf(listOf(UserAvatarDBO("userName","avatarUrl")))
    val expected = listOf(UserAvatarDBO("userName","avatarUrl"))

    coEvery { userDao.getAll() } returns daoResponse

    runBlocking {
      repository.getAll().collect { actual ->
        Assert.assertEquals(expected, actual)
      }
    }

    coVerify(exactly = 1) { userDao.getAll() }

    confirmVerified(userDao)
  }

  companion object {
    const val INSERTED = 1L
  }
}
