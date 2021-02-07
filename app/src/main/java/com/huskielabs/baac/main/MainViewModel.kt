package com.huskielabs.baac.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huskielabs.baac.domain.usecase.GetRandomEmojiUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import com.huskielabs.baac.util.DispatchersProvider
import com.huskielabs.baac.util.Reducer
import com.huskielabs.baac.util.ReducerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val getRandomEmojiUseCase: GetRandomEmojiUseCase,
  private val dispatchersProvider: DispatchersProvider,
) : MainContract.ViewModel, ViewModel(), Reducer<MainState> by ReducerImpl(MainState.INITIAL) {

  override fun getRandomEmoji() {
    viewModelScope.launch(dispatchersProvider.io) {
      updateState { copy(isRandomEmojiLoading = true) }

      try {
        val emojiUrl = getRandomEmojiUseCase(NoParams)

        updateState { copy(isRandomEmojiLoading = false, randomEmojiUrl = emojiUrl) }
      } catch (e: Exception) {
        updateState { copy(isRandomEmojiLoading = false) }
      }
    }
  }

}
