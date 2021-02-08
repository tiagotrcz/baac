package com.huskielabs.baac.main

data class MainState(
  val isRandomEmojiLoading: Boolean,
  val randomEmojiUrl: String?,
) {

  companion object {
    val INITIAL = MainState(
      isRandomEmojiLoading = false,
      randomEmojiUrl = null,
    )
  }
}
