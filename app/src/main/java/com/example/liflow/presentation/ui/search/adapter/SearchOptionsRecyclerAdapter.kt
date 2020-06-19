package com.example.liflow.presentation.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.databinding.ItemSearchOptionsBinding
import com.example.liflow.presentation.ui.search.model.SearchOptions

class SearchOptionsRecyclerAdapter(val list: List<SearchOptions>): RecyclerView.Adapter<SearchOptionsViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchOptionsViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
        val itemSearchOptionsBinding = ItemSearchOptionsBinding.inflate(inflater, parent, false)
        context = parent.context

        return SearchOptionsViewHolder(itemSearchOptionsBinding)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: SearchOptionsViewHolder, position: Int) {
        list[position].let { searchOption ->
            holder.bind(searchOption)
        }
    }
}