package com.example.liflow.domain.session

import com.example.liflow.domain.session.usecases.ClearSession
import com.example.liflow.domain.session.usecases.GetSessionToken
import com.example.liflow.domain.session.usecases.SetSessionToken
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class SessionDomain: ISessionDomain {
    private var getSessionTokenUC: GetSessionToken
    private var setSessionTokenUC: SetSessionToken
    private var clearSessionUC: ClearSession

    @Inject
    constructor(getSessionToken: GetSessionToken,
                setSessionToken: SetSessionToken,
                clearSession: ClearSession
    ) {
        this.getSessionTokenUC = getSessionToken
        this.setSessionTokenUC = setSessionToken
        this.clearSessionUC = clearSession
    }

    override fun getSessionToken(): GetSessionToken.Response {
        return getSessionTokenUC.execute()
    }

    override fun setSessionToken(
        observer: DisposableObserver<SetSessionToken.Response>,
        params: SetSessionToken.Params
    ) {
        return setSessionTokenUC.execute(observer, params)
    }

    override fun clearSession(
        observer: DisposableObserver<ClearSession.Response>,
        params: ClearSession.Params
    ) {
        return clearSessionUC.execute(observer, params)
    }
}