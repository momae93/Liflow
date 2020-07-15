package com.example.liflow.presentation.ui.category.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemCategoryPostThumbnailBinding
import com.example.liflow.databinding.ItemCategoryUserThumbnailBinding
import com.example.liflow.presentation.ui.category.model.PostThumbnail
import com.example.liflow.presentation.ui.category.model.UserThumbnail

class CategoryPostsViewHolder internal constructor(private val binding: ItemCategoryPostThumbnailBinding,
                                                   private val listener: CategoryPostsRecyclerAdapter.OnPostListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostThumbnail) {
        binding.post = post
        binding.root.setOnClickListener { listener.onPostClick(post.postId) }
    }
}