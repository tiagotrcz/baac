package com.huskielabs.baac.avatarlist

import kotlinx.coroutines.flow.StateFlow

interface AvatarListContract {

  interface ViewModel {

    val state: StateFlow<List<UserAvatarViewData>>

    fun getAllUsersAvatar()
    fun deleteAvatar(userAvatar: UserAvatarViewData)
  }
}
