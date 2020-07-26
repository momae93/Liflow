package com.example.liflow.presentation.ui.like.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemLikeCategoriesResultBinding
import com.example.liflow.databinding.ItemLikeUsersResultBinding
import com.example.liflow.presentation.ui.like.model.LikedCategory
import com.example.liflow.presentation.ui.like.model.LikedUser

class LikedCategoriesViewHolder internal constructor(private val binding: ItemLikeCategoriesResultBinding,
                                                     private val listener: LikedCategoriesRecyclerAdapter.OnCategoryListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: LikedCategory) {
        binding.category = category
        binding.root.setOnClickListener { listener.onCategoryClick(category.categoryId) }
    }
}