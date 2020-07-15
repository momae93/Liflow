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
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.category.adapter.CategoryDetailsUsersRecyclerAdapter
import com.example.liflow.presentation.ui.category.model.UserThumbnail
import com.example.liflow.presentation.ui.category.viewmodel.CategoryDetailsViewModel
import com.example.liflow.presentation.ui.profile.fragment.ProfilePostFragmentDirections
import javax.inject.Inject

class CategoryDetailsFragment : BaseFragment<FragmentCategoryDetailsBinding, CategoryDetailsViewModel, ICategoryDetailsNavigator>(),
    ICategoryDetailsNavigator,
    CategoryDetailsUsersRecyclerAdapter.OnUserListener{
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: CategoryDetailsViewModel
    private lateinit var viewBinding: FragmentCategoryDetailsBinding
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
        viewBinding = getViewBinding() as FragmentCategoryDetailsBinding
        categoryId?.let {
            viewModel.getCategoryDetails(it)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_category_details
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): CategoryDetailsViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(CategoryDetailsViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): ICategoryDetailsNavigator = this

    override fun initObservers() {
        super.initObservers()
        observeSearchCategories()
    }

    private fun observeSearchCategories() {
        viewModel.categoryDetails.observe(this, Observer { categoryDetails ->
            viewBinding.fragmentCategoryDetailsRecyclerViewUsers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewBinding.fragmentCategoryDetailsRecyclerViewUsers.adapter = CategoryDetailsUsersRecyclerAdapter(categoryDetails.authors, this
            )
        })
    }

    override fun onUserClick(userId: Int) {
        Toast.makeText(context, "Clicked on $userId", Toast.LENGTH_SHORT).show()
    }

    override fun navigateToCategoryPostsFragment() {
        categoryId?.let {
            val action = CategoryDetailsFragmentDirections
                .actionNavigationCategoryDetailsToNavigationCategoryPost(it)
            findNavController().navigate(action)
        }
    }
}
