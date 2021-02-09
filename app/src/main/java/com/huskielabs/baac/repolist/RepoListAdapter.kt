package com.huskielabs.baac.repolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huskielabs.baac.databinding.TextItemBinding

class RepoListAdapter : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

  private var repos = mutableListOf<UserRepoViewData>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      TextItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(repos[position])
  }

  override fun getItemCount(): Int = repos.size

  fun addList(list: List<UserRepoViewData>) {
    repos = (repos + list) as MutableList<UserRepoViewData>
    notifyDataSetChanged()
  }

  inner class ViewHolder(
    private val binding: TextItemBinding
  ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userRepoViewData: UserRepoViewData) {
      binding.text.text = userRepoViewData.fullName

      binding.root.setOnClickListener { }
    }
  }
}

