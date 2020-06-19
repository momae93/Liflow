package com.example.liflow.presentation.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemSearchUsersResultBinding
import com.example.liflow.presentation.ui.search.model.SearchedUser

class SearchedUsersRecyclerAdapter(val list: List<SearchedUser>): RecyclerView.Adapter<SearchedUsersViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedUsersViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemSearchedUsersBinding = ItemSearchUsersResultBinding.inflate(inflater, parent, false)
        context = parent.context

        return SearchedUsersViewHolder(itemSearchedUsersBinding)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: SearchedUsersViewHolder, position: Int) {
        list[position].let { searchedUsers ->
            holder.bind(searchedUsers)
        }
    }
}