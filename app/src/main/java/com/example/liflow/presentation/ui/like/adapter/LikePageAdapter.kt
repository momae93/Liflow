package com.example.liflow.presentation.ui.like.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.liflow.presentation.ui.like.fragment.LikeCategoriesFragment
import com.example.liflow.presentation.ui.like.fragment.LikePostsFragment
import com.example.liflow.presentation.ui.like.fragment.LikeUsersFragment

class LikePageAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    companion object {
        private const val NUM_PAGES = 3
        const val USERS_SCREEN_POSITION = 0
        const val POSTS_SCREEN_POSITION = 1
        const val CATEGORY_SCREEN_POSITION = 2
    }

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            USERS_SCREEN_POSITION -> LikeUsersFragment()
            POSTS_SCREEN_POSITION -> LikePostsFragment()
            CATEGORY_SCREEN_POSITION -> LikeCategoriesFragment()
            else -> throw Error("Unknown position ($position) for LikePageAdapter")
        }
    }
}