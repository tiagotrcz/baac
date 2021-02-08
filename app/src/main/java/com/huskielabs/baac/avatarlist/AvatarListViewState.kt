package com.huskielabs.baac.avatarlist

data class AvatarListViewState(
  val avatars: List<UserAvatarViewData>,
  val showEmptyView: Boolean,
) {

  companion object {
    val INITIAL = AvatarListViewState(
      avatars = emptyList(),
      showEmptyView = false,
    )
  }
}
