package com.huskielabs.baac.avatarlist

import kotlinx.coroutines.flow.StateFlow

interface AvatarListContract {

  interface ViewModel {

    val state: StateFlow<AvatarListState>

    fun getAllUsersAvatar()
    fun deleteAvatar(userAvatar: UserAvatarViewData)
  }
}
