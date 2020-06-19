package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.*
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class UserDomain: IUserDomain {
    private var getUserSessionUC: GetUserSession
    private var getUserProfileDetailsUC: GetUserProfileDetails
    private var getUserLikedPostsUC: GetUserLikedPosts
    private var getUserWrittenPostsUC: GetUserWrittenPosts
    private var getSearchedUsersUC: GetSearchedUsers

    @Inject
    constructor(getUserSession: GetUserSession,
                getUserProfileDetails: GetUserProfileDetails,
                getUserLikedPosts: GetUserLikedPosts,
                getUserWrittenPosts: GetUserWrittenPosts,
                getSearchedUsers: GetSearchedUsers
    ) {
        this.getUserSessionUC = getUserSession
        this.getUserProfileDetailsUC = getUserProfileDetails
        this.getUserLikedPostsUC = getUserLikedPosts
        this.getUserWrittenPostsUC = getUserWrittenPosts
        this.getSearchedUsersUC = getSearchedUsers
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

    override fun getSearchedUsers(
        observer: DisposableObserver<GetSearchedUsers.Response>,
        params: GetSearchedUsers.Params
    ) {
        return getSearchedUsersUC.execute(observer, params)
    }
}