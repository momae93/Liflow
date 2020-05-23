package com.example.liflow.presentation.ui.post.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.example.liflow.BR

import com.example.liflow.R
import com.example.liflow.databinding.FragmentPostDetailsBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.post.viewmodel.PostDetailsViewModel
import javax.inject.Inject
import javax.inject.Named

class PostDetailsFragment :
    BaseFragment<FragmentPostDetailsBinding, PostDetailsViewModel, IPostDetailsNavigator>(),
    IPostDetailsNavigator {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: PostDetailsViewModel
    private lateinit var viewBinding: FragmentPostDetailsBinding
    private var postId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val safeArgs = PostDetailsFragmentArgs.fromBundle(it)
            postId = safeArgs.postId
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentPostDetailsBinding
        postId?.let {
            viewModel.getPostDetails(it)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_post_details
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): PostDetailsViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory)
            .get(PostDetailsViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): IPostDetailsNavigator = this
}
