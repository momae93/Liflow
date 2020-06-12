package com.example.liflow.data.session

import android.content.SharedPreferences
import com.example.liflow.data.post.local.MockPostDatabase
import com.example.liflow.data.user.local.MockUserDatabase
import com.example.liflow.domain.session.ISessionRepository
import com.example.liflow.domain.session.usecases.ClearSession
import com.example.liflow.domain.session.usecases.GetSessionToken
import com.example.liflow.domain.session.usecases.SetSessionToken
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.domain.user.usecases.GetUserLikedPosts
import com.example.liflow.domain.user.usecases.GetUserProfileDetails
import com.example.liflow.domain.user.usecases.GetUserSession
import com.example.liflow.domain.user.usecases.GetUserWrittenPosts
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class SessionRepository @Inject constructor() : ISessionRepository {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val sessionTokenKey = "sessionToken"

    override fun getSessionToken(params: GetSessionToken.Params): GetSessionToken.Response {
        val sessionToken = sharedPreferences.getString(sessionTokenKey, null)
        return GetSessionToken.Response(sessionToken)
    }

    override fun setSessionToken(params: SetSessionToken.Params): Observable<SetSessionToken.Response> {
        val editor = sharedPreferences.edit()
        editor.putString(sessionTokenKey, params.sessionToken)
        editor.apply()

        return Observable.just(SetSessionToken.Response())
    }

    override fun clearSession(params: ClearSession.Params): Observable<ClearSession.Response> {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        return Observable.just(ClearSession.Response())
    }
}