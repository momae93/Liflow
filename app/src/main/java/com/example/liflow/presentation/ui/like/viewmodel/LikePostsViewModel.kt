package com.example.liflow.presentation.ui.like.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetLikedUsers
import com.example.liflow.domain.user.usecases.GetUserLikedPosts
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.like.fragment.ILikePostsNavigator
import com.example.liflow.presentation.ui.like.model.LikedPost
import com.example.liflow.presentation.ui.like.model.LikedUser
import javax.inject.Inject

class LikePostsViewModel: BaseViewModel<ILikePostsNavigator> {
    private var userDomain: UserDomain

    private var _likedPosts: MutableLiveData<List<LikedPost>> = MutableLiveData()
    val likedPosts: LiveData<List<LikedPost>> = _likedPosts

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun getAllLikedPosts() {
        userDomain.getUserLikedPosts(GetUserLikedPostsObserver(), GetUserLikedPosts.Params())
    }

    private inner class GetUserLikedPostsObserver : AbstractObserver<GetUserLikedPosts.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetUserLikedPosts.Response) {
            _likedPosts.value = responseData.list.map { LikedPost.map(it) }
        }
    }
}