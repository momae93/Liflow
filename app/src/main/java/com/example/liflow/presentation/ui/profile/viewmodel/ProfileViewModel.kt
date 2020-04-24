package com.example.liflow.presentation.ui.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetUserProfileDetails
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.models.State
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.profile.model.UserProfileDetails
import javax.inject.Inject

class ProfileViewModel: BaseViewModel {
    private var userDomain: UserDomain

    private var _userProfileDetailsLiveData: MutableLiveData<UserProfileDetails> = MutableLiveData()
    val profileDetails: LiveData<UserProfileDetails> = _userProfileDetailsLiveData

    private var _mname: MutableLiveData<String> = MutableLiveData()
    val mname: LiveData<String> = _mname

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun getUserProfileDetails(sessionToken: String) {
        _isLoading.value = true
        userDomain.getUserProfileDetails(GetProfileDetailsObserver(), GetUserProfileDetails.Params(sessionToken))
    }

    private inner class GetProfileDetailsObserver : AbstractObserver<GetUserProfileDetails.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetUserProfileDetails.Response) {
            val userProfileDetails = UserProfileDetails.map(responseData)
            _userProfileDetailsLiveData.value = userProfileDetails
            _mname.value = userProfileDetails.fullname
        }
    }
}