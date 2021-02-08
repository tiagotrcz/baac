package com.huskielabs.baac.main

import kotlinx.coroutines.flow.StateFlow

interface MainContract {

  interface ViewModel {

    val state: StateFlow<MainState>

    fun getRandomEmoji()
    fun openEmojiListScreen()
    fun searchAvatar(userName: String)
  }
}
