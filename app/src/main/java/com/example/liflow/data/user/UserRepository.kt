package com.example.liflow.data.user

import com.example.liflow.data.user.local.MockDatabase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.domain.user.usecases.GetUserLikedPosts
import com.example.liflow.domain.user.usecases.GetUserProfileDetails
import com.example.liflow.domain.user.usecases.GetUserSession
import com.example.liflow.domain.user.usecases.GetUserWrittenPosts
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UserRepository @Inject constructor() : IUserRepository {
    private val LOREM_IPSUM_IMAGE = "https://picsum.photos/200"

    override fun getUserSession(params: GetUserSession.Params): Observable<String> {
        val user = MockDatabase.mockUserData.find { it.password == params.password && it.username == params.username }
            ?: return Observable.error(Throwable("User does not exists"))

        val sessionToken = MockDatabase.mockUserSession.find { it.userId == user.id }
            ?: return Observable.error(Throwable("Token does not exists"))

        return Observable.just(sessionToken.token)
    }

    override fun getUserProfileDetails(params: GetUserProfileDetails.Params): Observable<GetUserProfileDetails.Response> {
        val sessionToken = MockDatabase.mockUserSession.find { it.token == params.sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val user = MockDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))

        val userProfileDetails = GetUserProfileDetails.Response(
            firstname = user.firstname,
            lastname = user.lastname,
            age = user.age,
            isMale = user.isMale,
            totalClap = user.totalClap,
            description = user.description,
            totalFollower = MockDatabase.mockFollowingUser.filter { it.followingUserId == user.id }.count(),
            totalFollowing = MockDatabase.mockFollowingUser.filter { it.userId == user.id }.count(),
            totalPostLiked = MockDatabase.mockLikedPost.filter{ it.userId == user.id }.count(),
            totalPostWritten = MockDatabase.mockPostData.filter { it.authorId == user.id }.count()
        )

        return Observable.just(userProfileDetails)
    }

    override fun getUserLikedPosts(params: GetUserLikedPosts.Params): Observable<GetUserLikedPosts.Response> {
        val sessionToken = MockDatabase.mockUserSession.find { it.token == params.sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val user = MockDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))

        val likedPostIds = MockDatabase.mockLikedPost
            .filter { it.userId == user.id }
            .map { it.postId }

        val likedPosts = MockDatabase
            .mockPostData
            .filter { likedPostIds.contains(it.id) }
            .map {
                GetUserLikedPosts.LikedPost(
                    id = it.id,
                    title = it.title,
                    category = it.category,
                    pictureUrl = LOREM_IPSUM_IMAGE
                )
            }

        return Observable.just(
            GetUserLikedPosts.Response(
                list = likedPosts
            )
        )
    }

    override fun getUserWrittenPosts(params: GetUserWrittenPosts.Params): Observable<GetUserWrittenPosts.Response> {
        val sessionToken = MockDatabase.mockUserSession.find { it.token == params.sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val user = MockDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))

        val writtenPosts = MockDatabase
            .mockPostData
            .filter { it.authorId == user.id }
            .map {
                GetUserWrittenPosts.WrittenPost(
                    id = it.id,
                    title = it.title,
                    category = it.category,
                    pictureUrl = LOREM_IPSUM_IMAGE
                )
            }

        return Observable.just(
            GetUserWrittenPosts.Response(
                list = writtenPosts
            )
        )
    }
}