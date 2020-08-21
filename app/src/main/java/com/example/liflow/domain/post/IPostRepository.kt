package com.example.liflow.domain.post

import com.example.liflow.domain.post.usecases.*
import io.reactivex.rxjava3.core.Observable

interface IPostRepository {
    fun getPostDetails(params: GetPostDetails.Params): Observable<GetPostDetails.Response>
    fun getRandomDailyPost(params: GetRandomDailyPost.Params): Observable<GetRandomDailyPost.Response>
    fun postLikePost(params: PostLikePost.Params): Observable<PostLikePost.Response>
    fun postClapsPost(params: PostClapsPost.Params): Observable<PostClapsPost.Response>
    fun postNewPost(params: PostNewPost.Params): Observable<PostNewPost.Response>
}