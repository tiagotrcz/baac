package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.usecase.GetAllUsersAvatarUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
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

class GetAllUsersAvatarUseCaseTest {

  private val userDataSource = mockk<UserDataSource>()

  private lateinit var useCase: GetAllUsersAvatarUseCase

  @Before
  fun `set up`() {
    useCase = GetAllUsersAvatarUseCase(userDataSource)
  }

  @Test
  fun `should get all users avatar`() {
    val dataSourceResponse = flowOf("avatarUrl")
    val expected = "avatarUrl"

    coEvery { userDataSource.getAll() } returns dataSourceResponse

    runBlocking {
      useCase(NoParams).collect { actual ->
        Assert.assertEquals(expected, actual)
      }
    }

    coVerify(exactly = 1) { userDataSource.getAll() }

    confirmVerified(userDataSource)
  }
}
