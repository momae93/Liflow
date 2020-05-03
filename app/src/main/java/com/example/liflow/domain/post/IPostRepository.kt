package com.example.liflow.domain.post

import com.example.liflow.domain.post.usecases.GetPostDetails
import com.example.liflow.domain.post.usecases.PostClapsPost
import com.example.liflow.domain.post.usecases.PostLikePost
import io.reactivex.rxjava3.core.Observable

interface IPostRepository {
    fun getPostDetails(params: GetPostDetails.Params): Observable<GetPostDetails.Response>
    fun postLikePost(params: PostLikePost.Params): Observable<PostLikePost.Response>
    fun postClapsPost(params: PostClapsPost.Params): Observable<PostClapsPost.Response>
}