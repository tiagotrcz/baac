package com.huskielabs.baac.avatarlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.huskielabs.baac.databinding.ImageItemBinding

class AvatarListAdapter : ListAdapter<UserAvatarViewData, AvatarListAdapter.ViewHolder>(
  DIFF_CALLBACK
) {

  lateinit var onItemClick: (UserAvatarViewData) -> Unit

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      ImageItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  inner class ViewHolder(
    private val binding: ImageItemBinding
  ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(userAvatar: UserAvatarViewData) {
      binding.imageEmoji.load(userAvatar.avatarUrl)

      binding.root.setOnClickListener { onItemClick(userAvatar) }
    }
  }

  companion object {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserAvatarViewData>() {
      override fun areItemsTheSame(
        oldItem: UserAvatarViewData,
        newItem: UserAvatarViewData
      ): Boolean = newItem.userName == oldItem.userName

      override fun areContentsTheSame(
        oldItem: UserAvatarViewData,
        newItem: UserAvatarViewData
      ): Boolean = newItem.userName == oldItem.userName
    }
  }

}
