package com.example.liflow.domain.user

import com.example.liflow.domain.user.usecases.*
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class UserDomain: IUserDomain {
    private var getUserSessionUC: GetUserSession
    private var getCurrentProfileDetailsUC: GetCurrentProfileDetails
    private var getUserLikedPostsUC: GetUserLikedPosts
    private var getUserWrittenPostsUC: GetUserWrittenPosts
    private var getSearchedUsersUC: GetSearchedUsers
    private var getLikedUsersUC: GetLikedUsers
    private var getLikedCategoriesUC: GetLikedCategories
    private var getProfileDetailsUC: GetProfileDetails
    private var postUserUC: PostUser

    @Inject
    constructor(getUserSession: GetUserSession,
                getCurrentProfileDetails: GetCurrentProfileDetails,
                getProfileDetails: GetProfileDetails,
                getUserLikedPosts: GetUserLikedPosts,
                getUserWrittenPosts: GetUserWrittenPosts,
                getSearchedUsers: GetSearchedUsers,
                getLikedUsers: GetLikedUsers,
                getLikedCategories: GetLikedCategories,
                postUser: PostUser
    ) {
        this.getUserSessionUC = getUserSession
        this.getCurrentProfileDetailsUC = getCurrentProfileDetails
        this.getProfileDetailsUC = getProfileDetails
        this.getUserLikedPostsUC = getUserLikedPosts
        this.getUserWrittenPostsUC = getUserWrittenPosts
        this.getSearchedUsersUC = getSearchedUsers
        this.getLikedUsersUC = getLikedUsers
        this.getLikedCategoriesUC = getLikedCategories
        this.getLikedCategoriesUC = getLikedCategories
        this.postUserUC = postUser
    }

    override fun getUserSession(observer: DisposableObserver<String>, params: GetUserSession.Params) {
        return getUserSessionUC.execute(observer, params)
    }

    override fun getCurrentProfileDetails(
        observer: DisposableObserver<GetCurrentProfileDetails.Response>,
        params: GetCurrentProfileDetails.Params
    ) {
        return getCurrentProfileDetailsUC.execute(observer, params)
    }

    override fun getProfileDetails(
        observer: DisposableObserver<GetProfileDetails.Response>,
        params: GetProfileDetails.Params
    ) {
        return getProfileDetailsUC.execute(observer, params)
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

    override fun getLikedUsers(
        observer: DisposableObserver<GetLikedUsers.Response>,
        params: GetLikedUsers.Params
    ) {
        return getLikedUsersUC.execute(observer, params)
    }

    override fun getLikedCategories(
        observer: DisposableObserver<GetLikedCategories.Response>,
        params: GetLikedCategories.Params
    ) {
        return getLikedCategoriesUC.execute(observer, params)
    }

    override fun postUser(observer: DisposableObserver<String>, params: PostUser.Params) {
        return postUserUC.execute(observer, params)
    }
}