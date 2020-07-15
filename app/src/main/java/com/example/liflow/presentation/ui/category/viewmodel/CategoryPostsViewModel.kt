package com.example.liflow.presentation.ui.category.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.category.CategoryDomain
import com.example.liflow.domain.category.usecases.GetCategoryPosts
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.category.fragment.ICategoryPostsNavigator
import com.example.liflow.presentation.ui.category.model.PostThumbnail
import javax.inject.Inject

class CategoryPostsViewModel : BaseViewModel<ICategoryPostsNavigator> {
    private var categoryDomain: CategoryDomain

    private var _postsMutableLiveData: MutableLiveData<List<PostThumbnail>> = MutableLiveData()
    val posts: LiveData<List<PostThumbnail>> = _postsMutableLiveData

    @Inject
    constructor(categoryDomain: CategoryDomain) {
        this.categoryDomain = categoryDomain
    }

    fun getCategoryDetails(categoryId: Int) {
        _isLoading.value = true
        categoryDomain.getCategoryPosts(GetCategoryPostsObserver(), GetCategoryPosts.Params(categoryId))
    }

    private inner class GetCategoryPostsObserver : AbstractObserver<GetCategoryPosts.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetCategoryPosts.Response) {
            val lists = responseData.list.map { PostThumbnail.map(it) }
            _postsMutableLiveData.value = lists
        }
    }
}
