package com.example.liflow.presentation.ui.register.model

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.liflow.presentation.models.FormField

class RegisterForm {
    val email: Email by lazy { Email() }
    val password: Password by lazy { Password() }
    val firstName: FirstName by lazy { FirstName() }
    val lastName: LastName by lazy { LastName() }
    val birthdate: BirthDate by lazy { BirthDate() }
    val isMale = MutableLiveData<Boolean>().apply { value = true }

    private val formFields = listOf( email, password, firstName, lastName, birthdate )


    fun validateForm(): Boolean {
        formFields.forEach { it.validate() }
        return formFields.all { it.valid.value == true }
    }

    class Email: FormField(errorMessage = "Email format is not valid") {
        override fun validate() {
            val value = fieldValue.value
            val isValid = !value.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(value).matches()
            this.valid.value = isValid
        }
    }

    class Password: FormField(errorMessage = "Password format is not valid") {
        override fun validate() {
            val isValid = !fieldValue.value.isNullOrEmpty()
            this.valid.value = isValid
        }
    }

    class FirstName: FormField(errorMessage = "First name format is not valid") {
        override fun validate() {
            val isValid = !fieldValue.value.isNullOrEmpty()
            this.valid.value = isValid
        }
    }

    class LastName: FormField(errorMessage = "Last name format is not valid") {
        override fun validate() {
            val isValid = !fieldValue.value.isNullOrEmpty()
            this.valid.value = isValid
        }
    }

    class BirthDate: FormField(errorMessage = "Birth date format is not valid") {
        override fun validate() {
            val isValid = !fieldValue.value.isNullOrEmpty()
            this.valid.value = isValid
        }
    }
}