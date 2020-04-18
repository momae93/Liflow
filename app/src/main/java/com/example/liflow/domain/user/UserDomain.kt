package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.GetUserProfileDetails
import com.example.liflow.domain.user.usecases.GetUserSession
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class UserDomain: IUserDomain {
    private var getUserSessionUC: GetUserSession
    private var getUserProfileDetailsUC: GetUserProfileDetails

    @Inject
    constructor(getUserSession: GetUserSession, getUserProfileDetails: GetUserProfileDetails) {
        this.getUserSessionUC = getUserSession
        this.getUserProfileDetailsUC = getUserProfileDetails
    }

    override fun getUserSession(observer: DisposableObserver<String>, params: GetUserSession.Params) {
        return getUserSessionUC.execute(observer, params)
    }

    override fun getUserProfileDetails(
        observer: DisposableObserver<GetUserProfileDetails.Response>,
        params: GetUserProfileDetails.Params
    ) {
        return getUserProfileDetailsUC.execute(observer, params)
    }
}