package com.huskielabs.baac.avatarlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huskielabs.baac.domain.usecase.DeleteUserAvatarUseCase
import com.huskielabs.baac.domain.usecase.GetAllUsersAvatarUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import com.huskielabs.baac.shared.DispatchersProvider
import com.huskielabs.baac.shared.Reducer
import com.huskielabs.baac.shared.ReducerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
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
) : ViewModel(), AvatarListContract.ViewModel,
  Reducer<AvatarListState> by ReducerImpl(AvatarListState.INITIAL) {

  override val state: StateFlow<AvatarListState> = mutableState

  override fun getAllUsersAvatar() {
    getAllUserAvatarUseCase(NoParams)
      .onEach { list ->
        val avatars = list.map { UserAvatarViewData(it.userName, it.avatarUrl) }
        updateState { copy(avatars = avatars, showEmptyView = avatars.isEmpty()) }
      }
      .catch { e ->
        e.printStackTrace()
        updateState { copy(avatars = emptyList(), showEmptyView = true) }
      }
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
        e.printStackTrace()
        //Do nothing...
      }
    }
  }

}
