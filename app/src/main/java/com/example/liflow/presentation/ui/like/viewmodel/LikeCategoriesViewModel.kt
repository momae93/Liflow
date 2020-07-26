package com.example.liflow.presentation.ui.like.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetLikedCategories
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.like.fragment.ILikeCategoriesNavigator
import com.example.liflow.presentation.ui.like.model.LikedCategory
import javax.inject.Inject

class LikeCategoriesViewModel: BaseViewModel<ILikeCategoriesNavigator> {
    private var userDomain: UserDomain

    private var _likedCategories: MutableLiveData<List<LikedCategory>> = MutableLiveData()
    val likedCategories: LiveData<List<LikedCategory>> = _likedCategories

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun getAllLikedCategory() {
        userDomain.getLikedCategories(GetLikedCategoriesObserver(), GetLikedCategories.Params())
    }

    private inner class GetLikedCategoriesObserver : AbstractObserver<GetLikedCategories.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetLikedCategories.Response) {
            _likedCategories.value = responseData.list.map { LikedCategory.map(it) }
        }
    }
}