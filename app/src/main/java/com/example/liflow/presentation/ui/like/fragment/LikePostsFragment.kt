package com.example.liflow.presentation.ui.like.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liflow.BR

import com.example.liflow.R
import com.example.liflow.databinding.FragmentLikePostsBinding
import com.example.liflow.databinding.FragmentLikeUsersBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.like.adapter.LikedPostsRecyclerAdapter
import com.example.liflow.presentation.ui.like.adapter.LikedUsersRecyclerAdapter
import com.example.liflow.presentation.ui.like.viewmodel.LikePostsViewModel
import com.example.liflow.presentation.ui.like.viewmodel.LikeUsersViewModel
import javax.inject.Inject

class LikePostsFragment :
    BaseFragment<FragmentLikePostsBinding, LikePostsViewModel, ILikePostsNavigator>(), ILikePostsNavigator,
    LikedPostsRecyclerAdapter.OnPostListener{

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewBinding: FragmentLikePostsBinding

    private lateinit var viewModel: LikePostsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentLikePostsBinding
        viewModel.getAllLikedPosts()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_like_posts
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): LikePostsViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(LikePostsViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): ILikePostsNavigator = this

    override fun navigateToUsersDetailsFragment(userId: Int) {
        Toast.makeText(context, "Navigating to $userId ...", Toast.LENGTH_SHORT).show()
    }

    override fun initObservers() {
        super.initObservers()
        observeLikedUsers()
    }

    private fun observeLikedUsers() {
        viewModel.likedPosts.observe(this, Observer { lists ->
            viewBinding.fragmentLikeFragmentsRecyclerViewPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewBinding.fragmentLikeFragmentsRecyclerViewPost.adapter = LikedPostsRecyclerAdapter(lists, this)
        })
    }

    override fun onPostClick(postId: Int) {
        navigateToUsersDetailsFragment(postId)
    }
}
