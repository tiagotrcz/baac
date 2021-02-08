package com.huskielabs.baac.emojilist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huskielabs.baac.domain.usecase.GetAllEmojisUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import com.huskielabs.baac.shared.DispatchersProvider
import com.huskielabs.baac.shared.Reducer
import com.huskielabs.baac.shared.ReducerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojiListViewModel @Inject constructor(
  private val getAllEmojisUseCase: GetAllEmojisUseCase,
  private val dispatchersProvider: DispatchersProvider,
) : ViewModel(), EmojiListContract.ViewModel,
  Reducer<EmojiListState> by ReducerImpl(EmojiListState.INITIAL) {

  private var emojiList = mutableListOf<String>()

  override val state: StateFlow<EmojiListState> = mutableState

  override fun getAllEmojis() {
    viewModelScope.launch(dispatchersProvider.io) {
      updateState { copy(loading = true, emojiUrlList = emptyList()) }

      try {
        emojiList = getAllEmojisUseCase(NoParams).toMutableList()

        updateState {
          copy(
            loading = false,
            emojiUrlList = emojiList,
            showEmptyView = emojiList.isEmpty()
          )
        }
      } catch (e: Exception) {
        updateState {
          copy(
            loading = false,
            emojiUrlList = emptyList(),
            showEmptyView = true
          )
        }
      }
    }
  }

}
