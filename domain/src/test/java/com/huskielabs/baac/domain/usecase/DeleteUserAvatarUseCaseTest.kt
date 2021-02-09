package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserAvatarModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteUserAvatarUseCaseTest {

  private val userDataSource = mockk<UserDataSource>()

  private lateinit var useCase: DeleteUserAvatarUseCase

  @Before
  fun `set up`() {
    useCase = DeleteUserAvatarUseCase(userDataSource)
  }

  @Test
  fun `should delete user avatar`() {
    val userName = "userName"
    val avatarUrl = "avatarUrl"
    val params = DeleteUserAvatarUseCase.Params(userName, avatarUrl)
    val userAvatarModel = UserAvatarModel(userName, avatarUrl)

    coEvery { userDataSource.deleteUserAvatar(userAvatarModel) } just runs

    runBlocking { useCase(params) }

    coVerify(exactly = 1) { userDataSource.deleteUserAvatar(userAvatarModel) }

    confirmVerified(userDataSource)
  }
}
