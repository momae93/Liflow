package com.example.liflow.presentation.ui.post.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liflow.BR

import com.example.liflow.R
import com.example.liflow.databinding.FragmentPostDetailsBinding
import com.example.liflow.databinding.FragmentProfilePostBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.post.model.PostDetails
import com.example.liflow.presentation.ui.post.viewmodel.IPostDetailsNavigator
import com.example.liflow.presentation.ui.post.viewmodel.PostDetailsViewModel
import com.example.liflow.presentation.ui.profile.adapter.PostRecyclerAdapter
import com.example.liflow.presentation.ui.profile.fragment.IProfilePostNavigator
import com.example.liflow.presentation.ui.profile.fragment.ProfilePostFragmentArgs
import com.example.liflow.presentation.ui.profile.viewmodel.ProfilePostViewModel
import javax.inject.Inject

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
            viewModel.getPostDetails("fOlmNZnpfP", it)
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
