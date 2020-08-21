package com.example.liflow.domain.post

import com.example.liflow.domain.post.usecases.*
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class PostDomain: IPostDomain {
    private var getPostDetailsUC: GetPostDetails
    private var getRandomDailyPostUC: GetRandomDailyPost
    private var postLikePostUC: PostLikePost
    private var postClapsPostUC: PostClapsPost
    private var postNewPostUC: PostNewPost

    @Inject
    constructor(
        getPostDetailsUC: GetPostDetails,
        postLikePostUC: PostLikePost,
        postClapsPostUC: PostClapsPost,
        getRandomDailyPostUC: GetRandomDailyPost,
        postNewPostUC: PostNewPost
    ) {
        this.getPostDetailsUC = getPostDetailsUC
        this.postLikePostUC = postLikePostUC
        this.postClapsPostUC = postClapsPostUC
        this.getRandomDailyPostUC = getRandomDailyPostUC
        this.postNewPostUC = postNewPostUC
    }

    override fun getPostDetails(
        observer: DisposableObserver<GetPostDetails.Response>,
        params: GetPostDetails.Params
    ) {
        getPostDetailsUC.execute(observer, params)
    }

    override fun getRandomDailyPost(
        observer: DisposableObserver<GetRandomDailyPost.Response>,
        params: GetRandomDailyPost.Params
    ) {
        getRandomDailyPostUC.execute(observer, params)
    }

    override fun postLikePost(
        observer: DisposableObserver<PostLikePost.Response>,
        params: PostLikePost.Params
    ) {
        postLikePostUC.execute(observer, params)
    }

    override fun postClapsPost(
        observer: DisposableObserver<PostClapsPost.Response>,
        params: PostClapsPost.Params
    ) {
        postClapsPostUC.execute(observer, params)
    }

    override fun postNewPost(
        observer: DisposableObserver<PostNewPost.Response>,
        params: PostNewPost.Params
    ) {
        postNewPostUC.execute(observer, params)
    }
}