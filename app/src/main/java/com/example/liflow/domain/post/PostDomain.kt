package com.example.liflow.domain.post

import com.example.liflow.domain.post.usecases.GetPostDetails
import com.example.liflow.domain.post.usecases.GetRandomDailyPost
import com.example.liflow.domain.post.usecases.PostClapsPost
import com.example.liflow.domain.post.usecases.PostLikePost
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class PostDomain: IPostDomain {
    private var getPostDetailsUC: GetPostDetails
    private var getRandomDailyPostUC: GetRandomDailyPost
    private var postLikePostUC: PostLikePost
    private var postClapsPostUC: PostClapsPost

    @Inject
    constructor(
        getPostDetailsUC: GetPostDetails,
        postLikePostUC: PostLikePost,
        postClapsPostUC: PostClapsPost,
        getRandomDailyPostUC: GetRandomDailyPost
    ) {
        this.getPostDetailsUC = getPostDetailsUC
        this.postLikePostUC = postLikePostUC
        this.postClapsPostUC = postClapsPostUC
        this.getRandomDailyPostUC = getRandomDailyPostUC
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
}