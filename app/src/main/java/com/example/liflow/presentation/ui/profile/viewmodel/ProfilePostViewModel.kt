package com.example.liflow.presentation.ui.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetUserLikedPosts
import com.example.liflow.domain.user.usecases.GetUserWrittenPosts
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.profile.fragment.IProfilePostNavigator
import com.example.liflow.presentation.ui.profile.model.PostThumbnail
import javax.inject.Inject

class ProfilePostViewModel : BaseViewModel<IProfilePostNavigator> {
    private var userDomain: UserDomain

    private var _postsMutableLiveData: MutableLiveData<List<PostThumbnail>> = MutableLiveData()
    val posts: LiveData<List<PostThumbnail>> = _postsMutableLiveData

    private var _titleMutableLiveData: MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String> = _titleMutableLiveData

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun setTitle(isLikedPostsCategory: Boolean) {
        _titleMutableLiveData.value = if (isLikedPostsCategory) "Posts liked" else "Posts written"
    }

    fun getUserWrittenPosts() {
        _isLoading.value = true
        userDomain.getUserWrittenPosts(GetUserWrittenPostsObserver(), GetUserWrittenPosts.Params())
    }

    private inner class GetUserWrittenPostsObserver : AbstractObserver<GetUserWrittenPosts.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetUserWrittenPosts.Response) {
            val writtenPosts = PostThumbnail.map(responseData)
            _postsMutableLiveData.value = writtenPosts
        }
    }
}
