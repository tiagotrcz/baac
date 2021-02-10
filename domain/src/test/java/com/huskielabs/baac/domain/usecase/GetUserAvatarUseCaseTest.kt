package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.exception.UserAvatarNotFoundException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetUserAvatarUseCaseTest {

  private val userDataSource = mockk<UserDataSource>()

  private lateinit var useCase: GetUserAvatarUseCase

  @Before
  fun `set up`() {
    useCase = GetUserAvatarUseCase(userDataSource)
  }

  @Test
  fun `should get user avatar by user name`() {
    val userName = "userName"
    val dataSourceResponse = "avatarUrl"
    val params = GetUserAvatarUseCase.Params("userName")
    val expected = "avatarUrl"

    coEvery { userDataSource.getUserAvatarByUserName(userName) } returns dataSourceResponse

    val actual = runBlocking { useCase(params) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { userDataSource.getUserAvatarByUserName(userName) }

    confirmVerified(userDataSource)
  }

  @Test(expected = UserAvatarNotFoundException::class)
  fun `should throw UserAvatarNotFoundException if data source response is null`() {
    val userName = "userName"
    val dataSourceResponse = null
    val params = GetUserAvatarUseCase.Params("userName")

    coEvery { userDataSource.getUserAvatarByUserName(userName) } returns dataSourceResponse

    runBlocking { useCase(params) }

    coVerify(exactly = 1) { userDataSource.getUserAvatarByUserName(userName) }

    confirmVerified(userDataSource)
  }
}