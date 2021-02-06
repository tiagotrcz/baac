package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.exception.UserAvatarNotFoundException
import com.huskielabs.baac.domain.usecase.shared.UseCase
import javax.inject.Inject

class GetUserAvatarUseCase @Inject constructor(
  private val userDataSource: UserDataSource
) : UseCase<GetUserAvatarUseCase.Params, String> {

  override suspend fun invoke(params: Params): String {
    return userDataSource.getUserAvatarByUserName(params.userName)
      ?: throw UserAvatarNotFoundException()
  }

  data class Params(val userName: String)

}
