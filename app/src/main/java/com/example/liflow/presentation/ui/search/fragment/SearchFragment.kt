package com.example.liflow.presentation.ui.search.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.liflow.BR
import com.example.liflow.R
import com.example.liflow.databinding.FragmentSearchBinding
import com.example.liflow.presentation.ui.ViewModelProviderFactory
import com.example.liflow.presentation.ui.base.BaseFragment
import com.example.liflow.presentation.ui.profile.adapter.PostRecyclerAdapter
import com.example.liflow.presentation.ui.profile.fragment.ProfilePostFragmentDirections
import com.example.liflow.presentation.ui.search.adapter.SearchOptionsRecyclerAdapter
import com.example.liflow.presentation.ui.search.adapter.SearchedCategoriesRecyclerAdapter
import com.example.liflow.presentation.ui.search.adapter.SearchedUsersRecyclerAdapter
import com.example.liflow.presentation.ui.search.model.SearchOptions
import com.example.liflow.presentation.ui.search.viewmodel.SearchViewModel
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel, ISearchNavigator>(),
    ISearchNavigator, SearchedCategoriesRecyclerAdapter.OnCategoryListener {
    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var viewModel: SearchViewModel
    private lateinit var viewBinding: FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = getViewBinding() as FragmentSearchBinding
        initSearchComponent()
        viewModel.getAllCategory()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_search
    }

    override fun getViewModelBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): SearchViewModel {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(SearchViewModel::class.java)
        return viewModel
    }

    override fun getNavigator(): ISearchNavigator = this

    override fun navigateToUserDetails(userId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToCategoryDetails(categoryId: Int) {
        val action = SearchFragmentDirections
            .actionNavigationSearchToNavigationCategoryDetails(categoryId)
        findNavController().navigate(action)
    }

    private fun initSearchComponent() {
        val list = listOf(
            SearchOptions("Users", R.drawable.ic_user_filled),
            SearchOptions("Categories", R.drawable.ic_label)
        )
        val adapter = SearchOptionsRecyclerAdapter(list)
        viewBinding.fragmentSearchViewpagerOptions.adapter = adapter
        viewBinding.fragmentSearchViewpagerOptions.registerOnPageChangeCallback(object :
            OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.onSearchOptionChanged(list[position].name)
            }
        })

        viewBinding.fragmentSearchEditTextSearch.textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { viewModel.search(it.toString().toLowerCase()) }
    }

    override fun initObservers() {
        super.initObservers()
        observeSearchUsers()
        observeSearchCategories()
    }

    private fun observeSearchUsers() {
        viewModel.searchedUsers.observe(this, Observer { lists ->
            viewBinding.fragmentSearchRecyclerViewSearchResults.layoutManager = LinearLayoutManager(context)
            viewBinding.fragmentSearchRecyclerViewSearchResults.adapter = SearchedUsersRecyclerAdapter(lists)
        })
    }

    private fun observeSearchCategories() {
        viewModel.searchedCategories.observe(this, Observer { lists ->
            viewBinding.fragmentSearchRecyclerViewSearchResults.layoutManager = LinearLayoutManager(context)
            viewBinding.fragmentSearchRecyclerViewSearchResults.adapter = SearchedCategoriesRecyclerAdapter(lists, this)
        })
    }

    override fun onCategoryClick(categoryId: Int) {
        navigateToCategoryDetails(categoryId)
    }
}