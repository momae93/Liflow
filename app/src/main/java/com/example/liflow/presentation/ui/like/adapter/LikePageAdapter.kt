package com.example.liflow.presentation.ui.like.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.liflow.presentation.ui.like.fragment.LikeCategoriesFragment
import com.example.liflow.presentation.ui.like.fragment.LikeUsersFragment

class LikePageAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    companion object {
        private const val NUM_PAGES = 2
        const val USERS_SCREEN_POSITION = 0
        const val CATEGORY_SCREEN_POSITION = 1
    }

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            USERS_SCREEN_POSITION -> LikeUsersFragment()
            CATEGORY_SCREEN_POSITION -> LikeCategoriesFragment()
            else -> throw Error("Unknown position ($position) for LikePageAdapter")
        }
    }
}