package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserAvatarModel
import com.huskielabs.baac.domain.usecase.shared.UseCase
import javax.inject.Inject

class DeleteUserAvatarUseCase @Inject constructor(
  private val userDataSource: UserDataSource,
) : UseCase<DeleteUserAvatarUseCase.Params, Unit> {

  override suspend fun invoke(params: Params) {
    userDataSource.deleteUserAvatar(
      UserAvatarModel(
        params.userName,
        params.avatarUrl,
      )
    )
  }

  data class Params(val userName: String, val avatarUrl: String)

}
