package com.huskielabs.baac.avatarlist

data class AvatarListState(
  val avatars: List<UserAvatarViewData>,
  val showEmptyView: Boolean,
) {

  companion object {
    val INITIAL = AvatarListState(
      avatars = emptyList(),
      showEmptyView = false,
    )
  }
}
