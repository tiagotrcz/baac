package com.huskielabs.baac.emojilist

import kotlinx.coroutines.flow.StateFlow

interface EmojiListContract {

  interface ViewModel {

    val state: StateFlow<EmojiListState>

    fun getAllEmojis()

  }
}
