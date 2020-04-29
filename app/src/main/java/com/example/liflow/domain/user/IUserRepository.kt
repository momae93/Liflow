package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.GetUserLikedPosts
import com.example.liflow.domain.user.usecases.GetUserProfileDetails
import com.example.liflow.domain.user.usecases.GetUserSession
import com.example.liflow.domain.user.usecases.GetUserWrittenPosts
import io.reactivex.rxjava3.core.Observable

interface IUserRepository {
    fun getUserSession(params: GetUserSession.Params): Observable<String>
    fun getUserProfileDetails(params: GetUserProfileDetails.Params): Observable<GetUserProfileDetails.Response>
    fun getUserLikedPosts(params: GetUserLikedPosts.Params): Observable<GetUserLikedPosts.Response>
    fun getUserWrittenPosts(params: GetUserWrittenPosts.Params): Observable<GetUserWrittenPosts.Response>
}