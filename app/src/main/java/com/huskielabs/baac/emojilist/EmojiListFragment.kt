package com.huskielabs.baac.emojilist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.huskielabs.baac.R
import com.huskielabs.baac.databinding.EmojiListFragmentBinding
import com.huskielabs.baac.shared.viewBinding
import com.huskielabs.baac.shared.watch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmojiListFragment : Fragment(R.layout.emoji_list_fragment) {

  private val binding by viewBinding(EmojiListFragmentBinding::bind)
  private val viewModel: EmojiListContract.ViewModel by viewModels<EmojiListViewModel>()

  @Inject
  lateinit var emojiListAdapter: EmojiListAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.recyclerEmoji.adapter = emojiListAdapter

    viewModel.getAllEmojis()

    bindInputs()
    bindOutputs()
  }

  private fun bindInputs() = with(binding) {
    swipeRefresh.setOnRefreshListener { viewModel.getAllEmojis() }
  }

  private fun bindOutputs() = with(viewModel) {
    watch(state) { state ->
      with(binding) {
        progressEmojiList.isVisible = state.loading
        recyclerEmoji.isVisible = !state.loading
        textEmpty.isVisible = state.showEmptyView

        loadAdapter(state.emojiUrlList)

        swipeRefresh.isRefreshing = false
      }
    }
  }

  private fun loadAdapter(emojiUrlList: List<String>) {
    emojiListAdapter.submitList(emojiUrlList)
  }
}
