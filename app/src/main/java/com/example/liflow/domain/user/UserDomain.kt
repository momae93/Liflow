package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.GetUserLikedPosts
import com.example.liflow.domain.user.usecases.GetUserProfileDetails
import com.example.liflow.domain.user.usecases.GetUserSession
import com.example.liflow.domain.user.usecases.GetUserWrittenPosts
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class UserDomain: IUserDomain {
    private var getUserSessionUC: GetUserSession
    private var getUserProfileDetailsUC: GetUserProfileDetails
    private var getUserLikedPostsUC: GetUserLikedPosts
    private var getUserWrittenPostsUC: GetUserWrittenPosts

    @Inject
    constructor(getUserSession: GetUserSession,
                getUserProfileDetails: GetUserProfileDetails,
                getUserLikedPosts: GetUserLikedPosts,
                getUserWrittenPosts: GetUserWrittenPosts
    ) {
        this.getUserSessionUC = getUserSession
        this.getUserProfileDetailsUC = getUserProfileDetails
        this.getUserLikedPostsUC = getUserLikedPosts
        this.getUserWrittenPostsUC = getUserWrittenPosts
    }

    override fun getUserSession(observer: DisposableObserver<String>, params: GetUserSession.Params) {
        return getUserSessionUC.execute(observer, params)
    }

    override fun getUserProfileDetails(
        observer: DisposableObserver<GetUserProfileDetails.Response>,
        params: GetUserProfileDetails.Params
    ) {
        return getUserProfileDetailsUC.execute(observer, params)
    }

    override fun getUserLikedPosts(
        observer: DisposableObserver<GetUserLikedPosts.Response>,
        params: GetUserLikedPosts.Params
    ) {
        return getUserLikedPostsUC.execute(observer, params)
    }

    override fun getUserWrittenPosts(
        observer: DisposableObserver<GetUserWrittenPosts.Response>,
        params: GetUserWrittenPosts.Params
    ) {
        return getUserWrittenPostsUC.execute(observer, params)
    }
}