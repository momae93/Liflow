package com.example.liflow.presentation.ui.like.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemCategoryUserThumbnailBinding
import com.example.liflow.databinding.ItemLikeCategoriesResultBinding
import com.example.liflow.databinding.ItemLikeUsersResultBinding
import com.example.liflow.presentation.ui.like.model.LikedCategory
import com.example.liflow.presentation.ui.like.model.LikedUser

class LikedCategoriesRecyclerAdapter(val list: List<LikedCategory>, private val categoryListener: OnCategoryListener): RecyclerView.Adapter<LikedCategoriesViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedCategoriesViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemSearchedCategoriesBinding = ItemLikeCategoriesResultBinding.inflate(inflater, parent, false)
        context = parent.context

        return LikedCategoriesViewHolder(itemSearchedCategoriesBinding, categoryListener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: LikedCategoriesViewHolder, position: Int) {
        list[position].let { category ->
            holder.bind(category)
        }
    }

    interface OnCategoryListener {
        fun onCategoryClick(userId: Int)
    }
}