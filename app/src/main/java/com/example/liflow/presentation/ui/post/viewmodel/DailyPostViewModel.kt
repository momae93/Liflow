package com.example.liflow.presentation.ui.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.post.PostDomain
import com.example.liflow.domain.post.usecases.GetRandomDailyPost
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.post.fragment.IDailyPostNavigator
import com.example.liflow.presentation.ui.post.model.DailyRandomPostThumbnail
import javax.inject.Inject

class DailyPostViewModel : BaseViewModel<IDailyPostNavigator> {
    private var postDomain: PostDomain

    private var _dailyRandomPostsMutableLiveData: MutableLiveData<List<DailyRandomPostThumbnail>> = MutableLiveData()
    val dailyRandomPosts: LiveData<List<DailyRandomPostThumbnail>> = _dailyRandomPostsMutableLiveData

    @Inject
    constructor(postDomain: PostDomain) {
        this.postDomain = postDomain
    }

    fun getRandomDailyPost() {
        _isLoading.value = true
        postDomain.getRandomDailyPost(GetRandomDailyPostObserver(), GetRandomDailyPost.Params())
    }

    private inner class GetRandomDailyPostObserver : AbstractObserver<GetRandomDailyPost.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetRandomDailyPost.Response) {
            val postDetails = DailyRandomPostThumbnail.map(responseData)
            _dailyRandomPostsMutableLiveData.value = listOf(postDetails)
        }
    }
}
