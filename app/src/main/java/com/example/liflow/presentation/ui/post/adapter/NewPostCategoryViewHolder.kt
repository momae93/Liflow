package com.example.liflow.presentation.ui.post.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemDailyRandomPostBinding
import com.example.liflow.databinding.ItemNewPostCategoryBinding
import com.example.liflow.presentation.ui.post.model.Category
import com.example.liflow.presentation.ui.post.model.DailyRandomPostThumbnail

class NewPostCategoryViewHolder internal constructor(
    private val binding: ItemNewPostCategoryBinding,
    private val listener: NewPostCategoryRecyclerAdapter.OnCategoryListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(categoryThumbnail: Category) {
        binding.category = categoryThumbnail
        binding.root.setOnClickListener { listener.onCategoryClick(categoryThumbnail.categoryId) }
    }
}