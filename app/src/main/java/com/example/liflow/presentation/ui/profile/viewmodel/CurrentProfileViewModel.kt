package com.example.liflow.presentation.ui.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.session.SessionDomain
import com.example.liflow.domain.session.usecases.ClearSession
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetCurrentProfileDetails
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.profile.fragment.ICurrentProfileNavigator
import com.example.liflow.presentation.ui.profile.model.UserProfileDetails
import javax.inject.Inject

class CurrentProfileViewModel: BaseViewModel<ICurrentProfileNavigator> {
    private var userDomain: UserDomain
    private var sessionDomain: SessionDomain

    private var _userProfileDetailsLiveData: MutableLiveData<UserProfileDetails> = MutableLiveData()
    val profileDetails: LiveData<UserProfileDetails> = _userProfileDetailsLiveData

    @Inject
    constructor(userDomain: UserDomain, sessionDomain: SessionDomain) {
        this.userDomain = userDomain
        this.sessionDomain = sessionDomain
    }

    fun onClickSwitchToWrittenPostsFragment () { getNavigator()?.navigateToWrittenPostsFragment() }
    fun onClickSwitchToBadgesFragment () { getNavigator()?.navigateToBadgesFragment() }

    fun onClickLogout () {
        removeSessionTokenLocally()
        getNavigator()?.navigateToLoginActivity()
    }

    fun getUserProfileDetails() {
        _isLoading.value = true
        userDomain.getCurrentProfileDetails(GetCurrentProfileDetailsObserver(), GetCurrentProfileDetails.Params())
    }

    private fun removeSessionTokenLocally() {
        sessionDomain.clearSession(ClearSessionObserver(), ClearSession.Params())
    }

    private inner class GetCurrentProfileDetailsObserver : AbstractObserver<GetCurrentProfileDetails.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetCurrentProfileDetails.Response) {
            val userProfileDetails = UserProfileDetails.map(responseData)
            _userProfileDetailsLiveData.value = userProfileDetails
        }
    }

    private inner class ClearSessionObserver : AbstractObserver<ClearSession.Response>() {
        override fun onComplete() {}

        override fun onError(e: Throwable) {
        }

        override fun onNext(responseData: ClearSession.Response) {
        }
    }
}