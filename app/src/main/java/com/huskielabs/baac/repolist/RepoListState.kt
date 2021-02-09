package com.huskielabs.baac.repolist

data class RepoListState(
  val repos: List<UserRepoViewData>,
  val showFirstPageLoading: Boolean,
  val isLoading: Boolean,
  val showMainError: Boolean,
  val showEmptyView: Boolean,
  val isLastPage: Boolean,
) {

  companion object {
    val INITIAL = RepoListState(
      repos = emptyList(),
      showFirstPageLoading = false,
      isLoading = false,
      showMainError = false,
      showEmptyView = false,
      isLastPage = false,
    )
  }
}
