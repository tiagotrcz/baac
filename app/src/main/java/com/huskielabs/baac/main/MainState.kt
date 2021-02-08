package com.huskielabs.baac.main

data class MainState(
  val isRandomEmojiLoading: Boolean,
  val imageUrl: String?,
) {

  companion object {
    val INITIAL = MainState(
      isRandomEmojiLoading = false,
      imageUrl = null,
    )
  }
}
