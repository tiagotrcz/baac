package com.huskielabs.baac.main

data class MainState(
  val isImageLoading: Boolean,
  val imageUrl: String?,
) {

  companion object {
    val INITIAL = MainState(
      isImageLoading = false,
      imageUrl = null,
    )
  }
}
