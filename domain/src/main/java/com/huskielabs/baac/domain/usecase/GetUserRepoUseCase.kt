package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserRepoModel
import com.huskielabs.baac.domain.usecase.shared.UseCase
import javax.inject.Inject

class GetUserRepoUseCase @Inject constructor(
  private val userDataSource: UserDataSource,
) : UseCase<GetUserRepoUseCase.Params, List<UserRepoModel>> {

  override suspend fun invoke(params: Params): List<UserRepoModel> {
    return userDataSource.getUserRepo(
      params.userName,
      params.page,
      params.perPage,
    )
  }

  data class Params(
    val userName: String,
    val page: Int,
    val perPage: Int,
  )

}
