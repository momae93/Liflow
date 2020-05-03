package com.example.liflow.data.post

import com.example.liflow.data.post.local.MockPostDatabase
import com.example.liflow.data.post.local.model.LikedPost
import com.example.liflow.data.user.local.MockUserDatabase
import com.example.liflow.domain.post.IPostRepository
import com.example.liflow.domain.post.usecases.GetPostDetails
import com.example.liflow.domain.post.usecases.PostClapsPost
import com.example.liflow.domain.post.usecases.PostLikePost
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PostRepository @Inject constructor() : IPostRepository {
    override fun getPostDetails(params: GetPostDetails.Params): Observable<GetPostDetails.Response> {
        val post = MockPostDatabase.mockPostData.find { it.id == params.postId } ?: return Observable.error(Throwable("Post does not exists"))
        val postDetails = MockPostDatabase.mockPostDetails.find { it.postId == params.postId } ?: return Observable.error(Throwable("Post details does not exists"))
        val author = MockUserDatabase.mockUserData.find { it.id == post.authorId } ?: return Observable.error(Throwable("User does not exists"))

        return Observable.just(GetPostDetails.Response(
            postId = post.id,
            authorId = author.id,
            firstname = author.firstname,
            lastname = author.lastname,
            isMale = author.isMale,
            age = author.age,
            title = postDetails.title,
            reason = postDetails.reason,
            description = postDetails.description,
            totalClap = postDetails.totalClap,
            alreadyLiked = postDetails.alreadyLiked
        ))
    }

    override fun postLikePost(params: PostLikePost.Params): Observable<PostLikePost.Response> {
        val post = MockPostDatabase.mockPostData.find { it.id == params.postId } ?: return Observable.error(Throwable("Post does not exists"))
        val postDetails = MockPostDatabase.mockPostDetails.find { it.postId == params.postId } ?: return Observable.error(Throwable("Post details does not exists"))
        val author = MockUserDatabase.mockUserData.find { it.id == post.authorId } ?: return Observable.error(Throwable("User does not exists"))

        val sessionToken = MockUserDatabase.mockUserSession.find { it.token == params.sessionToken }
            ?: return Observable.error(Throwable("User token does not exists"))
        val likedUser = MockUserDatabase.mockUserData.find { it.id == sessionToken.userId }
            ?: return Observable.error(Throwable("User that liked the post does not exists"))

        if (params.isPostLiked) {
            MockPostDatabase.mockLikedPost.add(LikedPost(userId = likedUser.id, postId = params.postId))
        } else {
            MockPostDatabase.mockLikedPost.remove(LikedPost(userId = likedUser.id, postId = params.postId))
        }

        return Observable.just(PostLikePost.Response(
            postId = post.id,
            authorId = author.id,
            firstname = author.firstname,
            lastname = author.lastname,
            isMale = author.isMale,
            age = author.age,
            title = postDetails.title,
            reason = postDetails.reason,
            description = postDetails.description,
            totalClap = postDetails.totalClap,
            alreadyLiked = postDetails.alreadyLiked
        ))
    }

    override fun postClapsPost(params: PostClapsPost.Params): Observable<PostClapsPost.Response> {
        val post = MockPostDatabase.mockPostData.find { it.id == params.postId } ?: return Observable.error(Throwable("Post does not exists"))
        val postDetails = MockPostDatabase.mockPostDetails.find { it.postId == params.postId } ?: return Observable.error(Throwable("Post details does not exists"))
        val author = MockUserDatabase.mockUserData.find { it.id == post.authorId } ?: return Observable.error(Throwable("User does not exists"))

        //FIXME Clap should be modified

        return Observable.just(PostClapsPost.Response(
            postId = post.id,
            authorId = author.id,
            firstname = author.firstname,
            lastname = author.lastname,
            isMale = author.isMale,
            age = author.age,
            title = postDetails.title,
            reason = postDetails.reason,
            description = postDetails.description,
            totalClap = postDetails.totalClap,
            alreadyLiked = postDetails.alreadyLiked
        ))
    }
}