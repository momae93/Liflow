package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.GetUserProfileDetails
import com.example.liflow.domain.user.usecases.GetUserSession
import io.reactivex.rxjava3.observers.DisposableObserver

interface IUserDomain {
    fun getUserSession(observer: DisposableObserver<String>, params: GetUserSession.Params)
    fun getUserProfileDetails(observer: DisposableObserver<GetUserProfileDetails.Response>, params: GetUserProfileDetails.Params)
}