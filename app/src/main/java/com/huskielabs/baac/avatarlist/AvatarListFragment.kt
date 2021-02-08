package com.huskielabs.baac.avatarlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.huskielabs.baac.R
import com.huskielabs.baac.databinding.AvatarListFragmentBinding
import com.huskielabs.baac.shared.viewBinding
import com.huskielabs.baac.shared.watch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AvatarListFragment : Fragment(R.layout.avatar_list_fragment) {

  private val binding by viewBinding(AvatarListFragmentBinding::bind)
  private val viewModel: AvatarListContract.ViewModel by viewModels<AvatarListViewModel>()

  @Inject
  lateinit var adapter: AvatarListAdapter

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.recyclerAvatars.adapter = adapter

    bindInput()
    bindOutput()
  }

  private fun bindInput() {
    viewModel.getAllUsersAvatar()

    adapter.onItemClick = viewModel::deleteAvatar
  }

  private fun bindOutput() = with(viewModel) {
    watch(state, adapter::submitList)
  }

}
