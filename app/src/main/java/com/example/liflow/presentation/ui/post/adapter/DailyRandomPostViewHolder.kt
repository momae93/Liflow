package com.example.liflow.presentation.ui.post.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemDailyRandomPostBinding
import com.example.liflow.presentation.ui.post.model.DailyRandomPostThumbnail

class DailyRandomPostViewHolder internal constructor(
    private val binding: ItemDailyRandomPostBinding,
    private val listener: DailyRandomPostRecyclerAdapter.OnPostListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(dailyRandomPostThumbnail: DailyRandomPostThumbnail) {
        binding.post = dailyRandomPostThumbnail
        binding.root.setOnClickListener { listener.onPostThumbnailClick(dailyRandomPostThumbnail.postId) }
    }
}