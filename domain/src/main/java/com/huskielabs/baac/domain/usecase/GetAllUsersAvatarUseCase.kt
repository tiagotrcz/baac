package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserAvatarModel
import com.huskielabs.baac.domain.usecase.shared.FlowUseCase
import com.huskielabs.baac.domain.usecase.shared.NoParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersAvatarUseCase @Inject constructor(
  private val userDataSource: UserDataSource,
) : FlowUseCase<NoParams, List<UserAvatarModel>> {

  override fun invoke(params: NoParams): Flow<List<UserAvatarModel>> {
    return userDataSource.getAll()
  }

}
