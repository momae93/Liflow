package com.example.liflow.domain.session

import com.example.liflow.domain.session.usecases.ClearSession
import com.example.liflow.domain.session.usecases.GetSessionToken
import com.example.liflow.domain.session.usecases.SetSessionToken
import io.reactivex.rxjava3.core.Observable

interface ISessionRepository {
    fun getSessionToken(params: GetSessionToken.Params): GetSessionToken.Response
    fun setSessionToken(params: SetSessionToken.Params): Observable<SetSessionToken.Response>
    fun clearSession(params: ClearSession.Params): Observable<ClearSession.Response>
}