package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.GetUserLikedPosts
import com.example.liflow.domain.user.usecases.GetUserProfileDetails
import com.example.liflow.domain.user.usecases.GetUserSession
import com.example.liflow.domain.user.usecases.GetUserWrittenPosts
import io.reactivex.rxjava3.observers.DisposableObserver

interface IUserDomain {
    fun getUserSession(observer: DisposableObserver<String>, params: GetUserSession.Params)
    fun getUserProfileDetails(observer: DisposableObserver<GetUserProfileDetails.Response>, params: GetUserProfileDetails.Params)
    fun getUserLikedPosts(observer: DisposableObserver<GetUserLikedPosts.Response>, params: GetUserLikedPosts.Params)
    fun getUserWrittenPosts(observer: DisposableObserver<GetUserWrittenPosts.Response>, params: GetUserWrittenPosts.Params)
}