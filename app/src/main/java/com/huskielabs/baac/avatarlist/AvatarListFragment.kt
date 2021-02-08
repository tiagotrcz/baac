package com.huskielabs.baac.avatarlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.huskielabs.baac.R

class AvatarListFragment : Fragment() {

  companion object {
    fun newInstance() = AvatarListFragment()
  }

  private lateinit var viewModel: AvatarListViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.avatar_list_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(AvatarListViewModel::class.java)
    // TODO: Use the ViewModel
  }

}
