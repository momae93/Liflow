package com.example.liflow.presentation.ui.like.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetLikedUsers
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.like.fragment.ILikeUsersNavigator
import com.example.liflow.presentation.ui.like.model.LikedUser
import com.example.liflow.presentation.ui.search.fragment.ISearchNavigator
import javax.inject.Inject

class LikeUsersViewModel: BaseViewModel<ILikeUsersNavigator> {
    private var userDomain: UserDomain

    private var _likedUsers: MutableLiveData<List<LikedUser>> = MutableLiveData()
    val likedUsers: LiveData<List<LikedUser>> = _likedUsers

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun getAllLikedUsers() {
        userDomain.getLikedUsers(GetLikedUsersObserver(), GetLikedUsers.Params())
    }

    private inner class GetLikedUsersObserver : AbstractObserver<GetLikedUsers.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetLikedUsers.Response) {
            _likedUsers.value = responseData.list.map { LikedUser.map(it) }
        }
    }
}