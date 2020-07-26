package com.example.liflow.data.user

import android.util.Log
import com.example.liflow.data.category.local.MockCategoryDatabase
import com.example.liflow.data.post.local.MockPostDatabase
import com.example.liflow.data.user.local.MockUserDatabase
import com.example.liflow.domain.user.IUserRepository
import com.example.liflow.domain.user.usecases.*
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class UserRepository @Inject constructor() : IUserRepository {
    @Inject
    @JvmField
    @Named("sessionToken")
    internal var sessionToken: String? = null

    private val LOREM_IPSUM_IMAGE = "https://picsum.photos/200"

    override fun getUserSession(params: GetUserSession.Params): Observable<String> {
        val user = MockUserDatabase.mockUserData.find { it.password == params.password && it.username == params.username }
            ?: return Observable.error(Throwable("User does not exists"))

        val sessionToken = MockUserDatabase.mockUserSession.find { it.userId == user.id }
            ?: return Observable.error(Throwable("Token does not exists"))

        return Observable.just(sessionToken.token)
    }

    override fun getUserProfileDetails(params: GetUserProfileDetails.Params): Observable<GetUserProfileDetails.Response> {
        Log.d("SESSION_TOKEN_REPO", sessionToken.hashCode().toString())
        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val user = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))

        val userProfileDetails = GetUserProfileDetails.Response(
            firstname = user.firstname,
            lastname = user.lastname,
            age = user.age,
            isMale = user.isMale,
            totalClap = user.totalClap,
            description = user.description,
            totalFollower = MockUserDatabase.mockFollowingUser.filter { it.followingUserId == user.id }.count(),
            totalFollowing = MockUserDatabase.mockFollowingUser.filter { it.userId == user.id }.count(),
            totalBadges = MockPostDatabase.mockLikedPost.filter{ it.userId == user.id }.count(),
            totalPostWritten = MockPostDatabase.mockPostData.filter { it.authorId == user.id }.count()
        )

        return Observable.just(userProfileDetails)
    }

    override fun getUserLikedPosts(params: GetUserLikedPosts.Params): Observable<GetUserLikedPosts.Response> {
        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val user = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))

        val likedPostIds = MockPostDatabase.mockLikedPost
            .filter { it.userId == user.id }
            .map { it.postId }

        val likedPosts = MockPostDatabase
            .mockPostData
            .filter { likedPostIds.contains(it.id) }
            .map {
                GetUserLikedPosts.LikedPost(
                    authorId = it.authorId,
                    postId = it.id,
                    title = it.title,
                    categoryName = it.category,
                    categoryId = it.categoryId,
                    pictureUrl = LOREM_IPSUM_IMAGE,
                    alreadyLiked = true
                )
            }

        return Observable.just(
            GetUserLikedPosts.Response(
                list = likedPosts
            )
        )
    }

    override fun getUserWrittenPosts(params: GetUserWrittenPosts.Params): Observable<GetUserWrittenPosts.Response> {
        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val user = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))

        val writtenPosts = MockPostDatabase
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

    override fun getSearchedUsers(params: GetSearchedUsers.Params): Observable<GetSearchedUsers.Response> {
        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val currentUser = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))
        val filteredUsers = MockUserDatabase.mockUserData.filter {
            it.firstname.toLowerCase().contains(params.searchPattern) || it.lastname.toLowerCase().contains(params.searchPattern)
        }

        val searchedUsers = filteredUsers.map { user ->
            val alreadyLiked = MockUserDatabase.mockFollowingUser.find { it.followingUserId == currentUser.id} !== null
            val totalPostPublished = MockPostDatabase.mockPostData.filter { it.authorId == user.id }.count()
            GetSearchedUsers.User(
                userId = user.id,
                firstname = user.firstname,
                lastname = user.lastname,
                pictureUrl = null,
                alreadyLiked = alreadyLiked,
                totalPostPublished = totalPostPublished
            )
        }

        return Observable.just(
            GetSearchedUsers.Response(
                list = searchedUsers
            )
        )
    }

    override fun getLikedUsers(params: GetLikedUsers.Params): Observable<GetLikedUsers.Response> {
        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val currentUser = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))
        val likedUserIdx = MockUserDatabase.mockFollowingUser.filter { it.followingUserId == currentUser.id }.map { it.userId }
        val filteredUsers = MockUserDatabase.mockUserData.filter { likedUserIdx.contains(it.id) }

        val likedUsers = filteredUsers.map { user ->
            GetLikedUsers.User(
                userId = user.id,
                firstname = user.firstname,
                lastname = user.lastname,
                pictureUrl = user.pictureUrl,
                alreadyLiked = true,
                totalNewPosts = 10
            )
        }

        return Observable.just(
            GetLikedUsers.Response(
                list = likedUsers
            )
        )
    }

    override fun getLikedCategories(params: GetLikedCategories.Params): Observable<GetLikedCategories.Response> {
        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val currentUser = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User does not exists"))
        val likedCategoryIdx = MockCategoryDatabase.mockLikedCategory.filter { it.userId == currentUser.id }.map { it.categoryId }
        val filteredCategories = MockCategoryDatabase.mockCategory.filter { likedCategoryIdx.contains(it.id) }

        val likedCategories = filteredCategories.map { category ->
            GetLikedCategories.Category(
                categoryId = category.id,
                name = category.name,
                pictureUrl = category.pictureUrl,
                alreadyLiked = true
            )
        }

        return Observable.just(
            GetLikedCategories.Response(
                list = likedCategories
            )
        )
    }
}