package com.example.liflow.presentation.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemSearchCategoriesResultBinding
import com.example.liflow.presentation.ui.search.model.SearchedCategory

class SearchedCategoriesViewHolder internal constructor(private val binding: ItemSearchCategoriesResultBinding,
                                                        private val listener: SearchedCategoriesRecyclerAdapter.OnCategoryListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(searchedCategory: SearchedCategory) {
        binding.category = searchedCategory
        binding.root.setOnClickListener { listener.onCategoryClick(searchedCategory.categoryId) }
    }
}