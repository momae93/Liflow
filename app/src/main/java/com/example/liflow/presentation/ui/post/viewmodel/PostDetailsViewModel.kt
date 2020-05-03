package com.example.liflow.presentation.ui.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.post.PostDomain
import com.example.liflow.domain.post.usecases.GetPostDetails
import com.example.liflow.domain.post.usecases.PostClapsPost
import com.example.liflow.domain.post.usecases.PostLikePost
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.post.model.PostDetails
import javax.inject.Inject

class PostDetailsViewModel : BaseViewModel<IPostDetailsNavigator> {
    private var postDomain: PostDomain

    private var _postDetailsMutableLiveData: MutableLiveData<PostDetails> = MutableLiveData()
    val postDetails: LiveData<PostDetails> = _postDetailsMutableLiveData

    private var _isLiked: MutableLiveData<Boolean> = MutableLiveData()
    val isLiked: LiveData<Boolean> = _isLiked

    private var _totalClaps: MutableLiveData<Int> = MutableLiveData()
    val totalClaps: LiveData<Int> = _totalClaps

    @Inject
    constructor(postDomain: PostDomain) {
        this.postDomain = postDomain
    }

    fun getPostDetails(sessionToken: String, postId: Int) {
        _isLoading.value = true
        postDomain.getPostDetails(GetPostDetailsObserver(), GetPostDetails.Params(sessionToken, postId))
    }

    fun onClickLikePost () {
        isLiked.value?.let {
            val newIsLiked = !it
            _isLiked.value = newIsLiked
            postDetails.value?.let {
                postDomain
                    .postLikePost(PostLikePostObserver(), PostLikePost.Params("fOlmNZnpfP", it.postId, newIsLiked))
            }
        }
    }

    fun onClickAddClap () {
        totalClaps.value?.let {
            val newClapNb = it + 1
            _totalClaps.value = newClapNb

            postDetails.value?.let {
                postDomain
                    .postClapsPost(PostClapsPostObserver(), PostClapsPost.Params("fOlmNZnpfP", it.postId, newClapNb))
            }
        }
    }

    private inner class GetPostDetailsObserver : AbstractObserver<GetPostDetails.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetPostDetails.Response) {
            val postDetails = PostDetails.map(responseData)
            _postDetailsMutableLiveData.value = postDetails
            _isLiked.value = responseData.alreadyLiked
            _totalClaps.value = responseData.totalClap
        }
    }

    private inner class PostLikePostObserver : AbstractObserver<PostLikePost.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: PostLikePost.Response) {}
    }

    private inner class PostClapsPostObserver : AbstractObserver<PostClapsPost.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: PostClapsPost.Response) {}
    }
}
