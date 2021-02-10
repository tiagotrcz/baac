package com.huskielabs.baac.domain.usecase

import com.huskielabs.baac.domain.datasource.UserDataSource
import com.huskielabs.baac.domain.model.UserRepoModel
import com.huskielabs.baac.domain.usecase.shared.UseCase
import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
  private val userDataSource: UserDataSource,
) : UseCase<GetUserReposUseCase.Params, List<UserRepoModel>> {

  override suspend fun invoke(params: Params): List<UserRepoModel> {
    return userDataSource.getUserRepos(params.page)
  }

  data class Params(
    val page: Int,
  )

}
