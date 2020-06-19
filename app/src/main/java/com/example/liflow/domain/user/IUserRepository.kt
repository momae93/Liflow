package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.*
import io.reactivex.rxjava3.core.Observable

interface IUserRepository {
    fun getUserSession(params: GetUserSession.Params): Observable<String>
    fun getUserProfileDetails(params: GetUserProfileDetails.Params): Observable<GetUserProfileDetails.Response>
    fun getUserLikedPosts(params: GetUserLikedPosts.Params): Observable<GetUserLikedPosts.Response>
    fun getUserWrittenPosts(params: GetUserWrittenPosts.Params): Observable<GetUserWrittenPosts.Response>
    fun getSearchedUsers(params: GetSearchedUsers.Params): Observable<GetSearchedUsers.Response>
}