package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.*
import io.reactivex.rxjava3.observers.DisposableObserver

interface IUserDomain {
    fun getUserSession(observer: DisposableObserver<String>, params: GetUserSession.Params)
    fun getCurrentProfileDetails(observer: DisposableObserver<GetCurrentProfileDetails.Response>, params: GetCurrentProfileDetails.Params)
    fun getProfileDetails(observer: DisposableObserver<GetProfileDetails.Response>, params: GetProfileDetails.Params)
    fun getUserLikedPosts(observer: DisposableObserver<GetUserLikedPosts.Response>, params: GetUserLikedPosts.Params)
    fun getUserWrittenPosts(observer: DisposableObserver<GetUserWrittenPosts.Response>, params: GetUserWrittenPosts.Params)
    fun getSearchedUsers(observer: DisposableObserver<GetSearchedUsers.Response>, params: GetSearchedUsers.Params)
    fun getLikedUsers(observer: DisposableObserver<GetLikedUsers.Response>, params: GetLikedUsers.Params)
    fun getLikedCategories(observer: DisposableObserver<GetLikedCategories.Response>, params: GetLikedCategories.Params)
    fun postUser(observer: DisposableObserver<String>, params: PostUser.Params)
}   