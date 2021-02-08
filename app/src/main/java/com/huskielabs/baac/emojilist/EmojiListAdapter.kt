package com.huskielabs.baac.emojilist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.huskielabs.baac.databinding.EmojiItemBinding

class EmojiListAdapter : RecyclerView.Adapter<EmojiListAdapter.ViewHolder>() {

  private var list = mutableListOf<String>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
      EmojiItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false,
      )
    )
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position])
  }

  override fun getItemCount(): Int = list.count()

  fun submitList(list: List<String>) {
    this.list = list.toMutableList()
  }

  private fun removeItem(position: Int) {
    list.removeAt(position)
    notifyItemRemoved(position)
  }

  inner class ViewHolder(
    private val binding: EmojiItemBinding
  ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(emojiUrl: String) {
      binding.imageEmoji.load(emojiUrl)

      binding.root.setOnClickListener { removeItem(adapterPosition) }
    }
  }

}
