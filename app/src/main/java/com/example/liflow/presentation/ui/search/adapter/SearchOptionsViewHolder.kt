package com.example.liflow.presentation.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemProfilePostThumbnailBinding
import com.example.liflow.databinding.ItemSearchOptionsBinding
import com.example.liflow.presentation.ui.profile.model.PostThumbnail
import com.example.liflow.presentation.ui.search.model.SearchOptions

class SearchOptionsViewHolder internal constructor(private val binding: ItemSearchOptionsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(searchOptions: SearchOptions) {
        binding.model = searchOptions
    }
}