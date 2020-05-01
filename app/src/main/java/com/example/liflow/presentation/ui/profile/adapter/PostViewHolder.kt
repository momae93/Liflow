package com.example.liflow.presentation.ui.profile.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemProfilePostThumbnailBinding
import com.example.liflow.presentation.ui.profile.model.PostThumbnail

class PostViewHolder internal constructor(private val binding: ItemProfilePostThumbnailBinding,
                                          private val listener: PostRecyclerAdapter.OnPostListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(postThumbnail: PostThumbnail) {
        binding.post = postThumbnail
        binding.root.setOnClickListener { listener.onPostThumbnailClick(postThumbnail.id) }
    }
}