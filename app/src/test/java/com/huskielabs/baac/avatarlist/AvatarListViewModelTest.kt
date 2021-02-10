package com.huskielabs.baac.avatarlist

import com.huskielabs.baac.AppDispatchersProviderTest
import com.huskielabs.baac.domain.model.UserAvatarModel
import com.huskielabs.baac.domain.usecase.DeleteUserAvatarUseCase
import com.huskielabs.baac.domain.usecase.GetAllUsersAvatarUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AvatarListViewModelTest {

  private val getAllUserAvatarUseCase = mockk<GetAllUsersAvatarUseCase>()
  private val deleteUserAvatarUseCase = mockk<DeleteUserAvatarUseCase>()

  private val dispatchersProvider = AppDispatchersProviderTest()

  private lateinit var viewModel: AvatarListContract.ViewModel

  @Before
  fun `set up`() {
    Dispatchers.setMain(dispatchersProvider.test)
    viewModel = AvatarListViewModel(
      getAllUserAvatarUseCase = getAllUserAvatarUseCase,
      deleteUserAvatarUseCase = deleteUserAvatarUseCase,
      dispatchersProvider = dispatchersProvider,
    )
  }

  @After
  fun `clean up`() {
    Dispatchers.resetMain()
  }

  @Test
  fun `should get all users avatar and update state with avatars list if success`() {
    val useCaseResponse = flow {
      emit(listOf(UserAvatarModel("userName", "avatarUrl")))
    }
    val expected = AvatarListState(
      avatars = listOf(UserAvatarViewData("userName", "avatarUrl")),
      showEmptyView = false,
    )

    coEvery { getAllUserAvatarUseCase(NoParams) } returns useCaseResponse

    dispatchersProvider.test.runBlockingTest { viewModel.getAllUsersAvatar() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getAllUserAvatarUseCase(NoParams) }

    confirmVerified(getAllUserAvatarUseCase, deleteUserAvatarUseCase)
  }

  @Test
  fun `should get all users avatar and update state with empty avatars list if error`() {
    val useCaseResponse = flow<List<UserAvatarModel>> { throw Exception() }
    val expected = AvatarListState(
      avatars = emptyList(),
      showEmptyView = true,
    )

    coEvery { getAllUserAvatarUseCase(NoParams) } returns useCaseResponse

    dispatchersProvider.test.runBlockingTest { viewModel.getAllUsersAvatar() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getAllUserAvatarUseCase(NoParams) }

    confirmVerified(getAllUserAvatarUseCase, deleteUserAvatarUseCase)
  }

  @Test
  fun `should delete avatar`() {
    val avatar = UserAvatarViewData("userName", "avatarUrl")
    val params = DeleteUserAvatarUseCase.Params("userName", "avatarUrl")

    coEvery { deleteUserAvatarUseCase(params) } just runs

    dispatchersProvider.test.runBlockingTest { viewModel.deleteAvatar(avatar) }

    coVerify(exactly = 1) { deleteUserAvatarUseCase(params) }

    confirmVerified(getAllUserAvatarUseCase, deleteUserAvatarUseCase)
  }

  @Test
  fun `should do nothing when delete avatar throw error`() {
    val avatar = UserAvatarViewData("userName", "avatarUrl")
    val params = DeleteUserAvatarUseCase.Params("userName", "avatarUrl")
    val exception = Exception()

    coEvery { deleteUserAvatarUseCase(params) } throws exception

    dispatchersProvider.test.runBlockingTest { viewModel.deleteAvatar(avatar) }

    coVerify(exactly = 1) { deleteUserAvatarUseCase(params) }

    confirmVerified(getAllUserAvatarUseCase, deleteUserAvatarUseCase)
  }

}
