package com.example.liflow.presentation.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemSearchUsersResultBinding
import com.example.liflow.presentation.ui.search.model.SearchedUser

class SearchedUsersViewHolder internal constructor(private val binding: ItemSearchUsersResultBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(searchedUser: SearchedUser) {
        binding.user = searchedUser
    }
}