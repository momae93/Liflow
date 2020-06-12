package com.example.liflow.domain.session

import com.example.liflow.domain.session.usecases.ClearSession
import com.example.liflow.domain.session.usecases.GetSessionToken
import com.example.liflow.domain.session.usecases.SetSessionToken
import io.reactivex.rxjava3.observers.DisposableObserver

interface ISessionDomain {
    fun getSessionToken(): GetSessionToken.Response
    fun setSessionToken(observer: DisposableObserver<SetSessionToken.Response>, params: SetSessionToken.Params)
    fun clearSession(observer: DisposableObserver<ClearSession.Response>, params: ClearSession.Params)
}