package com.example.liflow.presentation.ui.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.domain.AbstractObserver
import com.example.liflow.domain.category.CategoryDomain
import com.example.liflow.domain.category.usecases.GetAllCategory
import com.example.liflow.domain.post.PostDomain
import com.example.liflow.domain.post.usecases.PostNewPost
import com.example.liflow.domain.user.UserDomain
import com.example.liflow.domain.user.usecases.PostUser
import com.example.liflow.presentation.models.EErrorType
import com.example.liflow.presentation.models.ErrorHandler
import com.example.liflow.presentation.models.bindingAdapters.Event
import com.example.liflow.presentation.ui.base.BaseViewModel
import com.example.liflow.presentation.ui.post.fragment.INewPostNavigator
import com.example.liflow.presentation.ui.post.model.Category
import com.example.liflow.presentation.ui.post.model.ENewPostFormStep
import com.example.liflow.presentation.ui.post.model.NewPostForm
import com.example.liflow.presentation.ui.register.model.RegisterForm
import javax.inject.Inject

class NewPostViewModel: BaseViewModel<INewPostNavigator> {
    private var postDomain: PostDomain
    private var categoryDomain: CategoryDomain

    private var _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>> = _categories
    val form: NewPostForm = NewPostForm()

    private var accessedPosition: Boolean = false

    val cursorPosition = MediatorLiveData<Event<Int>>().apply {
        addSource(form.stepInformation.description.fieldValue) { value ->
            if(!accessedPosition) {
                setValue(Event(form.stepInformation.description.fieldValue.value?.length ?: 0))
                accessedPosition = true
            }
        }
    }

    @Inject
    constructor(postDomain: PostDomain, categoryDomain: CategoryDomain) {
        this.postDomain = postDomain
        this.categoryDomain = categoryDomain
    }

    fun onCategoryClick(categoryId: Int) {
        form.stepCategory.categoryId.fieldValue.value = categoryId
        handleNextStep()
    }

    private fun onCreateNewPost() {
        val isFormValid = form.validateForm()

        if (isFormValid) {
            val newPost = PostNewPost.Params(
                categoryId = form.stepCategory.categoryId.fieldValue.value!!,
                title = form.stepInformation.title.fieldValue.value!!,
                description = form.stepInformation.description.fieldValue.value!!,
                solution = form.stepInformation.solution.fieldValue.value!!
            )

            postDomain.postNewPost(PostNewPostObserver(), newPost)
        }
    }

    fun getAllCategory() {
        categoryDomain.getAllCategory(GetAllCategoryObserver(), GetAllCategory.Params())
    }

    fun handleNextStep() {
        when (form.currentStep.value) {
            ENewPostFormStep.CATEGORY -> form.currentStep.value = ENewPostFormStep.INFORMATION
            ENewPostFormStep.INFORMATION -> onCreateNewPost()
            else -> this.getNavigator()?.navigateBackToFragment()
        }
    }

    fun handlePreviousStep() {
        when (form.currentStep.value) {
            ENewPostFormStep.CATEGORY -> this.getNavigator()?.navigateBackToFragment()
            ENewPostFormStep.INFORMATION -> form.currentStep.value = ENewPostFormStep.CATEGORY
            else -> this.getNavigator()?.navigateBackToFragment()
        }
    }

    private inner class GetAllCategoryObserver : AbstractObserver<GetAllCategory.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: GetAllCategory.Response) {
            _categories.value = responseData.list.map { Category.map(it) }
        }
    }

    private inner class PostNewPostObserver : AbstractObserver<PostNewPost.Response>() {
        override fun onComplete() {
            _isLoading.value = false
        }

        override fun onError(e: Throwable) {
            _errorHandler.value = ErrorHandler(EErrorType.INTERNAL_SERVER, "An error has occurred")
        }

        override fun onNext(responseData: PostNewPost.Response) {
            this@NewPostViewModel.getNavigator()?.navigateBackToFragment()
        }
    }
}