package com.example.liflow.presentation.ui.like.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemLikePostsResultBinding
import com.example.liflow.presentation.ui.like.model.LikedPost

class LikedPostsRecyclerAdapter(val list: List<LikedPost>, private val postListener: OnPostListener): RecyclerView.Adapter<LikedPostsViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedPostsViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemLikePostResult = ItemLikePostsResultBinding.inflate(inflater, parent, false)
        context = parent.context

        return LikedPostsViewHolder(itemLikePostResult, postListener)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: LikedPostsViewHolder, position: Int) {
        list[position].let { post ->
            holder.bind(post)
        }
    }

    interface OnPostListener {
        fun onPostClick(postId: Int)
    }
}