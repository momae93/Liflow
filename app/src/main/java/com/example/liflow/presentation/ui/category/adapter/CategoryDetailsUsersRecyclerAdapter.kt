package com.example.liflow.presentation.ui.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemCategoryUserThumbnailBinding
import com.example.liflow.databinding.ItemSearchCategoriesResultBinding
import com.example.liflow.presentation.ui.category.model.UserThumbnail
import com.example.liflow.presentation.ui.search.model.SearchedCategory

class CategoryDetailsUsersRecyclerAdapter(val list: List<UserThumbnail>, private val userListener: OnUserListener): RecyclerView.Adapter<CategoryDetailsUsersViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailsUsersViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemSearchedCategoriesBinding = ItemCategoryUserThumbnailBinding.inflate(inflater, parent, false)
        context = parent.context

        return CategoryDetailsUsersViewHolder(itemSearchedCategoriesBinding, userListener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: CategoryDetailsUsersViewHolder, position: Int) {
        list[position].let { user ->
            holder.bind(user)
        }
    }

    interface OnUserListener {
        fun onUserClick(userId: Int)
    }
}