package com.example.liflow.presentation.ui.category.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemCategoryUserThumbnailBinding
import com.example.liflow.presentation.ui.category.model.UserThumbnail

class CategoryDetailsUsersViewHolder internal constructor(private val binding: ItemCategoryUserThumbnailBinding,
                                                          private val listener: CategoryDetailsUsersRecyclerAdapter.OnUserListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: UserThumbnail) {
        binding.user = user
        binding.root.setOnClickListener { listener.onUserClick(user.userId) }
    }
}