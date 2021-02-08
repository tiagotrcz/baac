package com.huskielabs.baac.avatarlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huskielabs.baac.domain.usecase.DeleteUserAvatarUseCase
import com.huskielabs.baac.domain.usecase.GetAllUsersAvatarUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import com.huskielabs.baac.shared.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvatarListViewModel @Inject constructor(
  private val getAllUserAvatarUseCase: GetAllUsersAvatarUseCase,
  private val deleteUserAvatarUseCase: DeleteUserAvatarUseCase,
  private val dispatchersProvider: DispatchersProvider,
) : ViewModel(), AvatarListContract.ViewModel {

  override val state = MutableStateFlow<List<UserAvatarViewData>>(emptyList())

  override fun getAllUsersAvatar() {
    getAllUserAvatarUseCase(NoParams)
      .onEach { list -> state.value = list.map { UserAvatarViewData(it.userName, it.avatarUrl) } }
      .flowOn(dispatchersProvider.io)
      .launchIn(viewModelScope)
  }

  override fun deleteAvatar(userAvatar: UserAvatarViewData) {
    viewModelScope.launch(dispatchersProvider.io) {
      try {
        deleteUserAvatarUseCase(
          DeleteUserAvatarUseCase.Params(
            userAvatar.userName,
            userAvatar.avatarUrl
          )
        )
      } catch (e: Exception) {

      }
    }
  }

}
