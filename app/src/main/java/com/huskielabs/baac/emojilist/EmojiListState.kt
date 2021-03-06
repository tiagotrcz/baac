package com.huskielabs.baac.emojilist

data class EmojiListState(
  val loading: Boolean,
  val emojiUrlList: List<String>,
  val showEmptyView: Boolean,
) {

  companion object {
    val INITIAL = EmojiListState(
      loading = false,
      emojiUrlList = emptyList(),
      showEmptyView = false,
    )
  }
}
