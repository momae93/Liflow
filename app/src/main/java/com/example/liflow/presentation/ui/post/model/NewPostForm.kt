package com.example.liflow.presentation.ui.post.model

import androidx.lifecycle.MutableLiveData
import com.example.liflow.presentation.models.FormField
import com.example.liflow.presentation.models.StepForm

class NewPostForm {
    val stepCategory: StepCategory by lazy { StepCategory() }
    val stepInformation: StepInformation by lazy { StepInformation() }
    val currentStep: MutableLiveData<ENewPostFormStep> = MutableLiveData<ENewPostFormStep>().apply { value = ENewPostFormStep.CATEGORY }

    private val formFields = listOf( stepCategory, stepInformation )

    fun validateForm(): Boolean {
        formFields.forEach { it.validate() }
        return formFields.all { it.valid.value == true }
    }

    //region Steps
    class StepCategory : StepForm() {
        val categoryId: CategoryId by lazy { CategoryId() }

        private val formFields = listOf( categoryId )

        //region Fields

        class CategoryId: FormField<Int>(errorMessage = "Category should not be empty") {

            override fun validate() {
                val value = fieldValue.value
                val isValid = value !== null
                this.valid.value = isValid
            }
        }

        //endregion

        override fun validate(): Boolean {
            formFields.forEach { it.validate() }
            return formFields.all { it.valid.value == true }
        }

        override fun resetFields() {
            formFields.forEach { it.fieldValue.value = null }
        }
    }

    class StepInformation : StepForm() {
        val title: Title by lazy { Title() }
        val description: Description by lazy { Description() }
        val solution: Solution by lazy { Solution() }
        private val formFields = listOf( title, description, solution )

        //region Fields
        class Title: FormField<String>(errorMessage = "Title format is not valid") {
            override fun validate() {
                val value = fieldValue.value
                val isValid = !value.isNullOrEmpty()
                this.valid.value = isValid
            }
        }

        class Description: FormField<String>(errorMessage = "Description format is not valid") {
            override fun validate() {
                val value = fieldValue.value
                val isValid = !value.isNullOrEmpty()
                this.valid.value = isValid
            }
        }

        class Solution: FormField<String>(errorMessage = "Solution format is not valid") {
            override fun validate() {
                val value = fieldValue.value
                val isValid = !value.isNullOrEmpty()
                this.valid.value = isValid
            }
        }

        override fun validate(): Boolean {
            formFields.forEach { it.validate() }
            return formFields.all { it.valid.value == true }
        }

        //endregion

        override fun resetFields() {
            formFields.forEach { it.fieldValue.value = null }
        }
    }
    //endregion
}