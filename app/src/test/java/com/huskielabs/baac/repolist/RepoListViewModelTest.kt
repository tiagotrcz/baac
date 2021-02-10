package com.huskielabs.baac.repolist

import com.huskielabs.baac.AppDispatchersProviderTest
import com.huskielabs.baac.domain.model.UserRepoModel
import com.huskielabs.baac.domain.usecase.GetUserReposUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepoListViewModelTest {

  private val getUserReposUseCase = mockk<GetUserReposUseCase>()

  private val dispatchersProvider = AppDispatchersProviderTest()

  private lateinit var viewModel: RepoListContract.ViewModel

  @Before
  fun `set up`() {
    Dispatchers.setMain(dispatchersProvider.test)
    viewModel = RepoListViewModel(
      getUserReposUseCase = getUserReposUseCase,
      dispatchersProvider = dispatchersProvider,
    )
  }

  @After
  fun `clean up`() {
    Dispatchers.resetMain()
  }

  @Test
  fun `should get user repo first page and set repo list`() {
    val params = GetUserReposUseCase.Params(page = 1)
    val useCaseResponse = listOf(UserRepoModel("fullName", "url"))
    val expected = RepoListState(
      repos = listOf(UserRepoViewData("fullName", "url")),
      showEmptyView = false,
      showFirstPageLoading = false,
      isLoading = false,
      isLastPage = false,
    )

    coEvery { getUserReposUseCase(params) } returns useCaseResponse

    dispatchersProvider.test.runBlockingTest { viewModel.getUserRepoFirstPage() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getUserReposUseCase(params) }

    confirmVerified(getUserReposUseCase)
  }

  @Test
  fun `should get user repo first page and set repo empty list`() {
    val params = GetUserReposUseCase.Params(page = 1)
    val exception = Exception()
    val expected = RepoListState(
      repos = emptyList(),
      showEmptyView = false,
      showFirstPageLoading = false,
      isLoading = false,
      isLastPage = false,
    )

    coEvery { getUserReposUseCase(params) } throws exception

    dispatchersProvider.test.runBlockingTest { viewModel.getUserRepoFirstPage() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getUserReposUseCase(params) }

    confirmVerified(getUserReposUseCase)
  }

  @Test
  fun `should get user repo next page and set repo list`() {
    val params = GetUserReposUseCase.Params(page = 2)
    val useCaseResponse = listOf(UserRepoModel("fullName", "url"))
    val expected = RepoListState(
      repos = listOf(UserRepoViewData("fullName", "url")),
      showEmptyView = false,
      showFirstPageLoading = false,
      isLoading = false,
      isLastPage = false,
    )

    coEvery { getUserReposUseCase(params) } returns useCaseResponse

    dispatchersProvider.test.runBlockingTest { viewModel.getUserRepoNextPage() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getUserReposUseCase(params) }

    confirmVerified(getUserReposUseCase)
  }

  @Test
  fun `should get user repo next page and set repo empty list`() {
    val params = GetUserReposUseCase.Params(page = 2)
    val exception = Exception()
    val expected = RepoListState(
      repos = emptyList(),
      showEmptyView = false,
      showFirstPageLoading = false,
      isLoading = false,
      isLastPage = false,
    )

    coEvery { getUserReposUseCase(params) } throws exception

    dispatchersProvider.test.runBlockingTest { viewModel.getUserRepoNextPage() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getUserReposUseCase(params) }

    confirmVerified(getUserReposUseCase)
  }
}
