package com.example.liflow.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetUserSession
import com.example.liflow.presentation.models.State
import javax.inject.Inject

class LoginViewModel: ViewModel {
    private var userDomain: UserDomain
    private var _loginStateLiveData: MutableLiveData<State<String>> = MutableLiveData()
    val loginStateLiveData: LiveData<State<String>> = _loginStateLiveData

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    @Inject
    constructor(userDomain: UserDomain) {
        this.userDomain = userDomain
    }

    fun onClickLogin() {
        val currentUsername = username.value
        val currentPassword = password.value

        if (currentUsername == null || currentPassword == null) {
            _loginStateLiveData.value = State.error("Something is wrong in the data")
            return
        }

        login(currentUsername, currentPassword)
    }

    private fun login(username: String, password: String) {
        _loginStateLiveData.value = State.loading()
        userDomain.getUserSession(GetUserSessionObserver(), GetUserSession.Params(username, password))
    }

    private inner class GetUserSessionObserver : AbstractObserver<String>() {
        override fun onComplete() {}

        override fun onError(e: Throwable) {
            _loginStateLiveData.value = State.error(e.message ?: "An error has occurred")
        }

        override fun onNext(responseData: String) {
            _loginStateLiveData.value = State.success(responseData)
        }
    }
}
