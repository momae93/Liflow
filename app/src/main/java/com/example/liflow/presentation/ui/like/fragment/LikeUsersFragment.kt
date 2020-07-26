package com.example.liflow.presentation.ui.like.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liflow.BR

import com.example.liflow.R
import com.example.liflow.databinding.FragmentLikeUsersBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.like.adapter.LikedUsersRecyclerAdapter
import com.example.liflow.presentation.ui.like.viewmodel.LikeUsersViewModel
import com.example.liflow.presentation.ui.search.fragment.SearchFragmentDirections
import javax.inject.Inject

class LikeUsersFragment :
    BaseFragment<FragmentLikeUsersBinding, LikeUsersViewModel, ILikeUsersNavigator>(), ILikeUsersNavigator,
    LikedUsersRecyclerAdapter.OnUserListener{
    companion object {
        fun newInstance() = LikeUsersFragment()
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    private lateinit var viewBinding: FragmentLikeUsersBinding

    private lateinit var viewModel: LikeUsersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentLikeUsersBinding
        viewModel.getAllLikedUsers()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_like_users
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): LikeUsersViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(LikeUsersViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): ILikeUsersNavigator = this

    override fun navigateToUsersDetailsFragment(userId: Int) {
        val action = LikeFragmentDirections
            .actionNavigationLikeToNavigationProfileDetails(userId)
        findNavController().navigate(action)    }

    override fun initObservers() {
        super.initObservers()
        observeLikedUsers()
    }

    private fun observeLikedUsers() {
        viewModel.likedUsers.observe(this, Observer { lists ->
            viewBinding.fragmentLikeUsersRecylerViewPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewBinding.fragmentLikeUsersRecylerViewPost.adapter = LikedUsersRecyclerAdapter(lists, this)
        })
    }

    override fun onUserClick(userId: Int) {
        navigateToUsersDetailsFragment(userId)
    }
}
