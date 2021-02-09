package com.huskielabs.baac.repolist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.huskielabs.baac.R
import com.huskielabs.baac.databinding.RepoListFragmentBinding
import com.huskielabs.baac.shared.PaginationScrollListener
import com.huskielabs.baac.shared.viewBinding
import com.huskielabs.baac.shared.watch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepoListFragment : Fragment(R.layout.repo_list_fragment) {

  private val binding by viewBinding(RepoListFragmentBinding::bind)
  private val viewModel: RepoListContract.ViewModel by viewModels<RepoListViewModel>()

  @Inject
  lateinit var adapter: RepoListAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.recyclerRepos.adapter = adapter

    bindInputs()
    bindOutputs()
  }

  private fun bindInputs() = with(binding) {
    viewModel.getUserRepoFirstPage()

    recyclerRepos.apply {
      addOnScrollListener(object : PaginationScrollListener(layoutManager as LinearLayoutManager) {
        override fun loadMoreItems() {
          viewModel.getUserRepoNextPage()
        }

        override fun isLoading(): Boolean = viewModel.state.value.isLoading

        override fun isLastPage(): Boolean = viewModel.state.value.isLastPage
      })
    }
  }

  private fun bindOutputs() = with(viewModel) {
    watch(state) { state ->
      adapter.addList(state.repos)

      with(binding) {
        progressRepoList.isVisible = state.showFirstPageLoading
        textEmpty.isVisible = state.showEmptyView
      }
    }
  }
}
