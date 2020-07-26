package com.example.liflow.presentation.ui.like.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liflow.BR

import com.example.liflow.R
import com.example.liflow.databinding.FragmentLikeCategoriesBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.like.adapter.LikedCategoriesRecyclerAdapter
import com.example.liflow.presentation.ui.like.viewmodel.LikeCategoriesViewModel
import com.example.liflow.presentation.ui.search.fragment.SearchFragmentDirections
import javax.inject.Inject

class LikeCategoriesFragment :
    BaseFragment<FragmentLikeCategoriesBinding, LikeCategoriesViewModel, ILikeCategoriesNavigator>(),
    ILikeCategoriesNavigator,
    LikedCategoriesRecyclerAdapter.OnCategoryListener{
    companion object {
        fun newInstance() = LikeCategoriesFragment()
    }

    private lateinit var viewBinding: FragmentLikeCategoriesBinding

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: LikeCategoriesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentLikeCategoriesBinding
        viewModel.getAllLikedCategory()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_like_categories
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): LikeCategoriesViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(LikeCategoriesViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): ILikeCategoriesNavigator = this

    override fun navigateToCategoryDetailsFragment(categoryId: Int) {
        val action = LikeFragmentDirections
            .actionNavigationLikeCategoriesToNavigationCategoryDetails(categoryId)
        findNavController().navigate(action)
    }

    override fun initObservers() {
        super.initObservers()
        observeLikedCategories()
    }

    private fun observeLikedCategories() {
        viewModel.likedCategories.observe(this, Observer { lists ->
            viewBinding.fragmentLikeCategoriesRecylerViewPost.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            viewBinding.fragmentLikeCategoriesRecylerViewPost.adapter = LikedCategoriesRecyclerAdapter(lists, this)
        })
    }

    override fun onCategoryClick(categoryId: Int) {
        navigateToCategoryDetailsFragment(categoryId)
    }
}
