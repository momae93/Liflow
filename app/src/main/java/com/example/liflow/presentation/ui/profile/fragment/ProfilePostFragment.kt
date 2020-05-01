package com.example.liflow.presentation.ui.profile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentProfilePostBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.profile.adapter.PostRecyclerAdapter
import com.example.liflow.presentation.ui.profile.viewmodel.ProfilePostViewModel
import javax.inject.Inject

class ProfilePostFragment :
    BaseFragment<FragmentProfilePostBinding, ProfilePostViewModel, IProfilePostNavigator>(),
    IProfilePostNavigator,
    PostRecyclerAdapter.OnPostListener {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: ProfilePostViewModel
    private lateinit var viewBinding: FragmentProfilePostBinding
    private var isLikedPostCategory: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val safeArgs = ProfilePostFragmentArgs.fromBundle(it)
            isLikedPostCategory = safeArgs.isLikedPostsCategory
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentProfilePostBinding
        isLikedPostCategory?.let {
            viewModel.setTitle(it)
            if (it) {
                viewModel.getUserLikedPosts("fOlmNZnpfP")
            }
            else {
                viewModel.getUserWrittenPosts("fOlmNZnpfP")
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile_post
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): ProfilePostViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory)
            .get(ProfilePostViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): IProfilePostNavigator = this

    override fun navigateToPostFragment(postId: Int) {
        val action = ProfilePostFragmentDirections
            .actionNavigationProfilePostToNavigationPostDetails(postId)
        findNavController().navigate(action)
    }

    override fun onPostThumbnailClick(postId: Int) {
        getNavigator().navigateToPostFragment(postId)
    }

    override fun initObservers() {
        super.initObservers()
        observeGetLikedPosts()
    }

    private fun observeGetLikedPosts() {
        viewModel.posts.observe(this, Observer { lists ->
            viewBinding.fragmentProfilePostRecylerViewPost.layoutManager = LinearLayoutManager(context)
            viewBinding.fragmentProfilePostRecylerViewPost.adapter = PostRecyclerAdapter(lists, this)
        })
    }
}

