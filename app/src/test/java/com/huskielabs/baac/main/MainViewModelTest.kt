package com.huskielabs.baac.main

import com.huskielabs.baac.AppDispatchersProviderTest
import com.huskielabs.baac.domain.usecase.GetRandomEmojiUseCase
import com.huskielabs.baac.domain.usecase.GetUserAvatarUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import com.huskielabs.baac.shared.Navigator
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

class MainViewModelTest {

  private val getRandomEmojiUseCase = mockk<GetRandomEmojiUseCase>()
  private val getUserAvatarUseCase = mockk<GetUserAvatarUseCase>()
  private val navigator = mockk<Navigator>()

  private val dispatchersProvider = AppDispatchersProviderTest()

  private lateinit var viewModel: MainContract.ViewModel

  @Before
  fun `set up`() {
    Dispatchers.setMain(dispatchersProvider.test)
    viewModel = MainViewModel(
      getRandomEmojiUseCase = getRandomEmojiUseCase,
      getUserAvatarUseCase = getUserAvatarUseCase,
      navigator = navigator,
      dispatchersProvider = dispatchersProvider,
    )
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `should get random emoji and then update state with image url if success`() {
    val url = "url"
    val expected = MainState(isImageLoading = false, imageUrl = "url")

    coEvery { getRandomEmojiUseCase(NoParams) } returns url

    dispatchersProvider.test.runBlockingTest { viewModel.getRandomEmoji() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getRandomEmojiUseCase(NoParams) }

    confirmVerified(getRandomEmojiUseCase, getUserAvatarUseCase, navigator)
  }

  @Test
  fun `should get random emoji and then update state image url as null if error`() {
    val exception = Exception()
    val expected = MainState(isImageLoading = false, imageUrl = null)

    coEvery { getRandomEmojiUseCase(NoParams) } throws exception

    dispatchersProvider.test.runBlockingTest { viewModel.getRandomEmoji() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getRandomEmojiUseCase(NoParams) }

    confirmVerified(getRandomEmojiUseCase, getUserAvatarUseCase, navigator)
  }

  @Test
  fun `should get user avatar and then update state with image url if success`() {
    val url = "url"
    val userName = "userName"
    val params = GetUserAvatarUseCase.Params(userName)
    val expected = MainState(isImageLoading = false, imageUrl = "url")

    coEvery { getUserAvatarUseCase(params) } returns url

    dispatchersProvider.test.runBlockingTest { viewModel.searchAvatar(userName) }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getUserAvatarUseCase(params) }

    confirmVerified(getRandomEmojiUseCase, getUserAvatarUseCase, navigator)
  }

  @Test
  fun `should get user avatar and then update state with image url as null if error`() {
    val exception = Exception()
    val userName = "userName"
    val params = GetUserAvatarUseCase.Params(userName)
    val expected = MainState(isImageLoading = false, imageUrl = null)

    coEvery { getUserAvatarUseCase(params) } throws exception

    dispatchersProvider.test.runBlockingTest { viewModel.searchAvatar(userName) }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getUserAvatarUseCase(params) }

    confirmVerified(getRandomEmojiUseCase, getUserAvatarUseCase, navigator)
  }

}
