package com.example.liflow.presentation.ui.like.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.liflow.BR

import com.example.liflow.R
import com.example.liflow.databinding.FragmentLikeBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.like.adapter.LikePageAdapter
import com.example.liflow.presentation.ui.like.viewmodel.LikeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class LikeFragment : BaseFragment<FragmentLikeBinding, LikeViewModel, ILikeNavigator>(), ILikeNavigator {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: LikeViewModel
    private lateinit var binding: FragmentLikeBinding
    private lateinit var viewPagerAdapter: LikePageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewBinding() as FragmentLikeBinding
        initViewPager()
    }

    private fun initViewPager() {
        viewPagerAdapter = LikePageAdapter(this)

        val tabLayout = binding.fragmentLikeTabLayoutTabs
        val viewPager = binding.fragmentLikeViewPagerOptions

        viewPager.apply {
            this.adapter = viewPagerAdapter
        }

        TabLayoutMediator(tabLayout, viewPager) { currentTab, currentPosition ->
            currentTab.text = when (currentPosition) {
                LikePageAdapter.USERS_SCREEN_POSITION -> "Users"
                LikePageAdapter.POSTS_SCREEN_POSITION -> "Posts"
                LikePageAdapter.CATEGORY_SCREEN_POSITION -> "Categories"
                else -> "Others"
            }
        }.attach()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_like
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): LikeViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(LikeViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): ILikeNavigator = this
}
