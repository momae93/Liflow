package com.example.liflow.domain.post

import com.example.liflow.domain.post.usecases.*
import io.reactivex.rxjava3.observers.DisposableObserver

interface IPostDomain {
    fun getPostDetails(observer: DisposableObserver<GetPostDetails.Response>, params: GetPostDetails.Params)
    fun getRandomDailyPost(observer: DisposableObserver<GetRandomDailyPost.Response>, params: GetRandomDailyPost.Params)
    fun postLikePost(observer: DisposableObserver<PostLikePost.Response>, params: PostLikePost.Params)
    fun postClapsPost(observer: DisposableObserver<PostClapsPost.Response>, params: PostClapsPost.Params)
    fun postNewPost(observer: DisposableObserver<PostNewPost.Response>, params: PostNewPost.Params)
}