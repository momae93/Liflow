package com.example.liflow.presentation.ui.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.category.CategoryDomain
import com.example.liflow.domain.category.usecases.GetCategoryDetails
import com.example.liflow.domain.post.usecases.GetPostDetails
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.category.fragment.ICategoryDetailsNavigator
import com.example.liflow.presentation.ui.category.model.CategoryDetails
import com.example.liflow.presentation.ui.post.model.PostDetails
import javax.inject.Inject

class CategoryDetailsViewModel : BaseViewModel<ICategoryDetailsNavigator> {
    private var categoryDomain: CategoryDomain

    private var _categoryDetailsMutableLiveData: MutableLiveData<CategoryDetails> = MutableLiveData()
    val categoryDetails: LiveData<CategoryDetails> = _categoryDetailsMutableLiveData

    private var _isLiked: MutableLiveData<Boolean> = MutableLiveData()
    val isLiked: LiveData<Boolean> = _isLiked

    @Inject
    constructor(categoryDomain: CategoryDomain) {
        this.categoryDomain = categoryDomain
    }

    fun onClickLikeCategory  () {
        isLiked.value?.let {
            val newIsLiked = !it
            _isLiked.value = newIsLiked
        }
    }

    fun onClickLoadCategoryPosts () {
        getNavigator()?.navigateToCategoryPostsFragment()
    }

    fun getCategoryDetails(categoryId: Int) {
        _isLoading.value = true
        categoryDomain.getCategoryDetails(GetCategoryDetailsObserver(), GetCategoryDetails.Params(categoryId))
    }

    private inner class GetCategoryDetailsObserver : AbstractObserver<GetCategoryDetails.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetCategoryDetails.Response) {
            val categoryDetails = CategoryDetails.map(responseData)
            _categoryDetailsMutableLiveData.value = categoryDetails
            _isLiked.value = responseData.alreadyLiked
        }
    }
}
