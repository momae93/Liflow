package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.GetUserSession
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class UserDomain: IUserDomain {
    private var getUserSessionUC: GetUserSession

    @Inject
    constructor(getUserSession: GetUserSession) {
        this.getUserSessionUC = getUserSession
    }

    override fun getUserSession(observer: DisposableObserver<String>, params: GetUserSession.Params) {
        return getUserSessionUC.execute(observer, params)
    }
}