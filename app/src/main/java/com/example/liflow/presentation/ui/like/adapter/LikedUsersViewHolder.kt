package com.example.liflow.presentation.ui.like.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemLikeUsersResultBinding
import com.example.liflow.presentation.ui.like.model.LikedUser

class LikedUsersViewHolder internal constructor(private val binding: ItemLikeUsersResultBinding,
                                                private val listener: LikedUsersRecyclerAdapter.OnUserListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: LikedUser) {
        binding.user = user
        binding.root.setOnClickListener { listener.onUserClick(user.userId) }
    }
}