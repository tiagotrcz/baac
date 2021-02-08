package com.huskielabs.baac.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.huskielabs.baac.R
import com.huskielabs.baac.databinding.MainFragmentBinding
import com.huskielabs.baac.shared.viewBinding
import com.huskielabs.baac.shared.watch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

  private val binding by viewBinding(MainFragmentBinding::bind)
  private val viewModel: MainContract.ViewModel by viewModels<MainViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    bindInputs()
    bindOutputs()
  }

  private fun bindInputs() = with(binding) {
    buttonRandomEmoji.setOnClickListener { viewModel.getRandomEmoji() }
    buttonOpenEmojiList.setOnClickListener { viewModel.openEmojiListScreen() }
    buttonSearchAvatar.setOnClickListener {
      viewModel.searchAvatar(editAvatarSearch.text.toString())
    }
    buttonOpenAvatarList.setOnClickListener { viewModel.openAvatarListScreen() }
  }

  private fun bindOutputs() = with(viewModel) {
    watch(state) { state ->
      with(binding) {
        imageEmojiAvatar.isVisible = !state.isRandomEmojiLoading
        progressBarRandomEmoji.isVisible = state.isRandomEmojiLoading

        state.imageUrl?.let(::loadImage)
      }
    }
  }

  private fun loadImage(imageUrl: String) {
    binding.imageEmojiAvatar.load(imageUrl)
  }

}
