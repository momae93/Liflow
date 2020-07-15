package com.example.liflow.presentation.ui.category.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentCategoryDetailsBinding
import com.example.liflow.databinding.FragmentCategoryPostsBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.category.adapter.CategoryPostsRecyclerAdapter
import com.example.liflow.presentation.ui.category.viewmodel.CategoryPostsViewModel
import javax.inject.Inject

class CategoryPostsFragment : BaseFragment<FragmentCategoryDetailsBinding, CategoryPostsViewModel, ICategoryPostsNavigator>(),
    ICategoryPostsNavigator,
    CategoryPostsRecyclerAdapter.OnPostListener{
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: CategoryPostsViewModel
    private lateinit var viewBinding: FragmentCategoryPostsBinding
    private var categoryId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val safeArgs = CategoryDetailsFragmentArgs.fromBundle(it)
            categoryId = safeArgs.categoryId
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentCategoryPostsBinding
        categoryId?.let {
            viewModel.getCategoryDetails(it)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_category_posts
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): CategoryPostsViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(CategoryPostsViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): ICategoryPostsNavigator = this

    override fun initObservers() {
        super.initObservers()
        observeSearchCategories()
    }

    private fun observeSearchCategories() {
        viewModel.posts.observe(this, Observer { lists ->
            viewBinding.fragmentCategoryPostsRecylerViewPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewBinding.fragmentCategoryPostsRecylerViewPost.adapter = CategoryPostsRecyclerAdapter(lists, this)
        })
    }

    override fun onPostClick(postId: Int) {
        navigateToCategoryDetailsFragment(postId)
    }

    override fun navigateToCategoryDetailsFragment(postId: Int) {
        val action = CategoryPostsFragmentDirections
            .actionNavigationCategoryPostToNavigationPostDetails(postId)
        findNavController().navigate(action)
    }
}
