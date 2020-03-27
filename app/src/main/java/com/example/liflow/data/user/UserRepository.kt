package com.example.liflow.data.user

import com.example.liflow.data.user.model.User
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.domain.user.usecases.GetUserSession
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() : IUserRepository {
    val fakeUserDatabase = listOf<User>(
        User(
            username = "micka",
            password = "mickapwd",
            firstname = "Mickael",
            sessionToken = "aBcDeF"
        )
    )
    override fun getUserSession(params: GetUserSession.Params): Observable<String> {
        val user = fakeUserDatabase.find { it.password == params.password && it.username == params.username }

        if (user !== null) {
            return Observable.just(user.sessionToken)
        }
        return Observable.error(Throwable("User does not exists"))
    }
}