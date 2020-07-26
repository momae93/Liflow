package com.example.liflow.presentation.ui.like.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemLikePostsResultBinding
import com.example.liflow.presentation.ui.like.model.LikedPost
import com.example.liflow.presentation.ui.like.model.LikedUser

class LikedPostsViewHolder internal constructor(private val binding: ItemLikePostsResultBinding,
                                                private val listener: LikedPostsRecyclerAdapter.OnPostListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: LikedPost) {
        binding.post = post
        binding.root.setOnClickListener { listener.onPostClick(post.postId) }
    }
}