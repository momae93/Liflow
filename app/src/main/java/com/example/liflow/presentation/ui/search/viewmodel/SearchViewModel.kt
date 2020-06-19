package com.example.liflow.presentation.ui.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.category.CategoryDomain
import com.example.liflow.domain.category.usecases.GetAllCategory
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetSearchedUsers
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.search.fragment.ISearchNavigator
import com.example.liflow.presentation.ui.search.model.SearchedCategory
import com.example.liflow.presentation.ui.search.model.SearchedUser
import javax.inject.Inject

class SearchViewModel: BaseViewModel<ISearchNavigator> {
    private var userDomain: UserDomain
    private var categoryDomain: CategoryDomain

    private lateinit var searchCategory: String
    private var searchPattern: String? = null
    private var categories = listOf<SearchedCategory>()

    private var _displayText: MutableLiveData<String?> = MutableLiveData()
    val displayText: LiveData<String?> = _displayText

    private var _searchedUsers: MutableLiveData<List<SearchedUser>> = MutableLiveData()
    val searchedUsers: LiveData<List<SearchedUser>> = _searchedUsers

    private var _searchedCategories: MutableLiveData<List<SearchedCategory>> = MutableLiveData()
    val searchedCategories: LiveData<List<SearchedCategory>> = _searchedCategories

    @Inject
    constructor(userDomain: UserDomain, categoryDomain: CategoryDomain) {
        this.userDomain = userDomain
        this.categoryDomain = categoryDomain
    }

    fun onSearchOptionChanged(option: String) {
        searchCategory = option
        searchByCategory()
    }

    fun search(searchPattern: String) {
        this.searchPattern = searchPattern
        searchByCategory()
    }

    private fun searchByCategory() {
        if (searchPattern.isNullOrBlank()) {
            _displayText.value =  "Please input a keyword to search in ${searchCategory}..."
            _searchedUsers.value = listOf()
            _searchedCategories.value = listOf()
        } else {
            _displayText.value = null
            when (searchCategory) {
                "Users" -> userDomain.getSearchedUsers(GetSearchedUsersObserver(), GetSearchedUsers.Params(searchPattern!!))
                "Categories" -> filterCategoriesBySearch(searchPattern!!)
            }
        }
    }

    fun getAllCategory() {
        categoryDomain.getAllCategory(GetAllCategoryObserver(), GetAllCategory.Params())
    }

    private fun filterCategoriesBySearch(searchPattern: String) {
        _searchedCategories.value = categories.filter { it.name.toLowerCase().contains(searchPattern) }
    }

    private inner class GetSearchedUsersObserver : AbstractObserver<GetSearchedUsers.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetSearchedUsers.Response) {
            _searchedUsers.value = responseData.list.map { SearchedUser.map(it) }
        }
    }

    private inner class GetAllCategoryObserver : AbstractObserver<GetAllCategory.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetAllCategory.Response) {
            categories = responseData.list.map { SearchedCategory.map(it) }
        }
    }
}