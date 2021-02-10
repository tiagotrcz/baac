package com.huskielabs.baac.repolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huskielabs.baac.domain.usecase.GetUserReposUseCase
import com.huskielabs.baac.shared.DispatchersProvider
import com.huskielabs.baac.shared.Reducer
import com.huskielabs.baac.shared.ReducerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
  private val getUserReposUseCase: GetUserReposUseCase,
  private val dispatchersProvider: DispatchersProvider,
) : ViewModel(), RepoListContract.ViewModel,
  Reducer<RepoListState> by ReducerImpl(RepoListState.INITIAL) {

  private var page = 1

  override val state: StateFlow<RepoListState> = mutableState

  override fun getUserRepoFirstPage() {
    viewModelScope.launch(dispatchersProvider.io) {
      updateState { copy(repos = emptyList(), showFirstPageLoading = true, isLoading = true) }

      try {
        val repos = getUserReposUseCase(GetUserReposUseCase.Params(page)).map {
          UserRepoViewData(it.fullName, it.url)
        }

        updateState {
          copy(
            repos = repos,
            showEmptyView = repos.isEmpty(),
            showFirstPageLoading = false,
            isLoading = false,
          )
        }
      } catch (e: Exception) {
        e.printStackTrace()
        updateState {
          copy(
            repos = emptyList(),
            showFirstPageLoading = false,
            isLoading = false,
          )
        }
      }
    }
  }

  override fun getUserRepoNextPage() {
    viewModelScope.launch(dispatchersProvider.io) {
      updateState { copy(repos = emptyList(), isLoading = true) }
      try {
        page++
        val repos = getUserReposUseCase(GetUserReposUseCase.Params(page)).map {
          UserRepoViewData(it.fullName, it.url)
        }

        updateState {
          copy(
            repos = repos,
            isLastPage = repos.isEmpty(),
            isLoading = false,
          )
        }
      } catch (e: Exception) {
        e.printStackTrace()
        updateState { copy(repos = emptyList(), isLoading = false) }
        //Do nothing...
      }
    }
  }

}
