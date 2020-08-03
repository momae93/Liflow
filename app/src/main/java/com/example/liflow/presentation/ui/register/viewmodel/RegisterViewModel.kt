package com.example.liflow.presentation.ui.register.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.session.SessionDomain
import com.example.liflow.domain.session.usecases.SetSessionToken
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.GetUserSession
import com.example.liflow.domain.user.usecases.PostUser
import com.example.liflow.presentation.models.State
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.register.model.RegisterForm
import com.example.liflow.presentation.ui.register.view.IRegisterNavigator
import javax.inject.Inject

class RegisterViewModel: BaseViewModel<IRegisterNavigator> {
    private var userDomain: UserDomain
    private var sessionDomain: SessionDomain

    val registerForm: RegisterForm = RegisterForm()

    @Inject
    constructor(userDomain: UserDomain, sessionDomain: SessionDomain) {
        this.userDomain = userDomain
        this.sessionDomain = sessionDomain
    }

    fun onGenderMaleClick() = run { registerForm.isMale.value = true }
    fun onGenderFemaleClick() = run { registerForm.isMale.value = false }

    fun onBirthDateClick() {
        getNavigator()?.openBirthDateDialog()
    }

    fun onRegister() {
        val isFormValid = registerForm.validateForm()

        if (isFormValid) {
            val newUser = PostUser.Params(
                email = registerForm.email.fieldValue.value!!,
                password = registerForm.password.fieldValue.value!!,
                lastName = registerForm.lastName.fieldValue.value!!,
                firstName = registerForm.firstName.fieldValue.value!!,
                isMale = registerForm.isMale.value!!,
                birthDate = registerForm.birthdate.fieldValue.value!!
            )

            userDomain.postUser(PostUserObserver(), newUser)
        }
    }

    private fun saveSessionTokenLocally(sessionToken: String) {
        sessionDomain.setSessionToken(SetSessionTokenObserver(), SetSessionToken.Params(sessionToken))
    }
    private inner class PostUserObserver: AbstractObserver<String>() {
        override fun onComplete() {}

        override fun onError(e: Throwable) {}

        override fun onNext(responseData: String) {
            saveSessionTokenLocally(responseData)
        }
    }

    private inner class SetSessionTokenObserver : AbstractObserver<SetSessionToken.Response>() {
        override fun onComplete() {}

        override fun onError(e: Throwable) {}

        override fun onNext(responseData: SetSessionToken.Response) {
            getNavigator()?.navigateToMainActivity()
        }
    }
}
