package com.huskielabs.baac.repolist

import kotlinx.coroutines.flow.StateFlow

interface RepoListContract {

  interface ViewModel {

    val state: StateFlow<RepoListState>

    fun getUserRepoFirstPage()
    fun getUserRepoNextPage()
  }
}
