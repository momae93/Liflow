package com.example.liflow.presentation.ui.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetCurrentProfileDetails
import com.example.liflow.domain.user.usecases.GetProfileDetails
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.profile.fragment.IProfileDetailsNavigator
import com.example.liflow.presentation.ui.profile.model.UserProfileDetails
import javax.inject.Inject

class ProfileDetailsViewModel: BaseViewModel<IProfileDetailsNavigator> {
    private var userDomain: UserDomain

    private var _userProfileDetailsLiveData: MutableLiveData<UserProfileDetails> = MutableLiveData()
    val profileDetails: LiveData<UserProfileDetails> = _userProfileDetailsLiveData

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun onClickSwitchToWrittenPostsFragment () { getNavigator()?.navigateToWrittenPostsFragment() }
    fun onClickSwitchToBadgesFragment () { getNavigator()?.navigateToBadgesFragment() }

    fun getProfileDetails(userId: Int) {
        _isLoading.value = true
        userDomain.getProfileDetails(GetProfileDetailsObserver(), GetProfileDetails.Params(userId))
    }

    private inner class GetProfileDetailsObserver : AbstractObserver<GetProfileDetails.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetProfileDetails.Response) {
            val userProfileDetails = UserProfileDetails.map(responseData)
            _userProfileDetailsLiveData.value = userProfileDetails
        }
    }
}