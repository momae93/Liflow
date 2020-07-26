package com.example.liflow.presentation.ui.like.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemCategoryUserThumbnailBinding
import com.example.liflow.databinding.ItemLikeUsersResultBinding
import com.example.liflow.presentation.ui.like.model.LikedUser

class LikedUsersRecyclerAdapter(val list: List<LikedUser>, private val userListener: OnUserListener): RecyclerView.Adapter<LikedUsersViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedUsersViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemSearchedCategoriesBinding = ItemLikeUsersResultBinding.inflate(inflater, parent, false)
        context = parent.context

        return LikedUsersViewHolder(itemSearchedCategoriesBinding, userListener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: LikedUsersViewHolder, position: Int) {
        list[position].let { user ->
            holder.bind(user)
        }
    }

    interface OnUserListener {
        fun onUserClick(userId: Int)
    }
}