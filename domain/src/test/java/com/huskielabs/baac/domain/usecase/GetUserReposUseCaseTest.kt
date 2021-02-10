package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserRepoModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetUserReposUseCaseTest {

  private val userDataSource = mockk<UserDataSource>()

  private lateinit var useCase: GetUserReposUseCase

  @Before
  fun `set up`() {
    useCase = GetUserReposUseCase(userDataSource)
  }

  @Test
  fun `should get user repos`() {
    val page = 1
    val params = GetUserReposUseCase.Params(page)
    val expected = listOf(UserRepoModel("fullName", "url"))

    coEvery { userDataSource.getUserRepos(page) } returns expected

    val actual = runBlocking { useCase(params) }
    Assert.assertEquals(expected, actual)

    coVerify(exactly = 1) { userDataSource.getUserRepos(page) }

    confirmVerified(userDataSource)
  }
}
