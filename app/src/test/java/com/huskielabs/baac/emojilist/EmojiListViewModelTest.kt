package com.huskielabs.baac.emojilist

import com.huskielabs.baac.AppDispatchersProviderTest
import com.huskielabs.baac.domain.usecase.GetAllEmojisUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
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

class EmojiListViewModelTest {

  private val getAllEmojisUseCase = mockk<GetAllEmojisUseCase>()

  private val dispatchersProvider = AppDispatchersProviderTest()

  private lateinit var viewModel: EmojiListContract.ViewModel

  @Before
  fun `set up`() {
    Dispatchers.setMain(dispatchersProvider.test)
    viewModel = EmojiListViewModel(
      getAllEmojisUseCase = getAllEmojisUseCase,
      dispatchersProvider = dispatchersProvider,
    )
  }

  @After
  fun `clean up`() {
    Dispatchers.resetMain()
  }

  @Test
  fun `should get emojis and then update state with emoji list if success`() {
    val expected = EmojiListState(
      loading = false,
      emojiUrlList = mutableListOf("emojiUrl"),
      showEmptyView = false,
    )
    val useCaseResponse = listOf("emojiUrl")

    coEvery { getAllEmojisUseCase(NoParams) } returns useCaseResponse

    dispatchersProvider.test.runBlockingTest { viewModel.getAllEmojis() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getAllEmojisUseCase(NoParams) }

    confirmVerified(getAllEmojisUseCase)
  }

  @Test
  fun `should get emojis and then update state with empty emoji list if error`() {
    val expected = EmojiListState(
      loading = false,
      emojiUrlList = mutableListOf(),
      showEmptyView = true,
    )
    val exception = Exception()

    coEvery { getAllEmojisUseCase(NoParams) } throws exception

    dispatchersProvider.test.runBlockingTest { viewModel.getAllEmojis() }
    Assert.assertEquals(expected, viewModel.state.value)

    coVerify(exactly = 1) { getAllEmojisUseCase(NoParams) }

    confirmVerified(getAllEmojisUseCase)
  }
}
